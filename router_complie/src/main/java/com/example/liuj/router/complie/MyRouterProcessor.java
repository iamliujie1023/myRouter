package com.example.liuj.router.complie;

import com.example.liuj.router.annotation.Router;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)//该标记表明可以处理注解的能力
public class MyRouterProcessor extends AbstractProcessor{

    private boolean fileExist;
    private Messager mMessager; //日志相关的辅助类
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
        print("init");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        print("process");

        // 生成 router.map
        Set<? extends Element> activities = roundEnv.getElementsAnnotatedWith(Router.class);
        MethodSpec.Builder mapMethod = MethodSpec.methodBuilder("map").addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);

        String fileName = "RouterMapping";
        for (Element act : activities) {
            if (act.getKind() != ElementKind.CLASS) {
                error("router annotation only use on Class, router注解必须运用在Class上");
            }

            Router router = act.getAnnotation(Router.class);
            for (String target : router.target()){
                mapMethod.addStatement("com.example.liuj.router.helper.MyRouter.map($S,$T.class)",
                        target,
                        ClassName.get((TypeElement) act));
            }

        }

        TypeSpec routerMapping = TypeSpec.classBuilder(fileName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(mapMethod.build())
                .build();

        if(!fileExist) {
            try {
                JavaFile.builder("com.example.liuj.router.mapping", routerMapping)
                        .build()
                        .writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileExist = true;
        }
        return true;
    }

    /**
     * 支持JDK版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        print("getSupportedSourceVersion");
        return SourceVersion.latestSupported();
    }

    /**
     * 包含本处理器想要处理的注解类型的合法全称。换句话说，你在这里定义你的注解处理器注册到哪些注解上。
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Router.class.getCanonicalName());
        print("getSupportedAnnotationTypes");
        return types;
    }

    private void print(String msg) {
        String str = String.format("***** %s *****", msg);
        mMessager.printMessage(Diagnostic.Kind.NOTE, str);
    }

    private void error(String msg) {
        String str = String.format("***** %s *****", msg);
        mMessager.printMessage(Diagnostic.Kind.ERROR, str);
    }


}