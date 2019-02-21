package com.example.jzycc.compiler;

import com.example.jzycc.lib_annotation.RouterUrl;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

//通过 AutoService 将 Processor 声明到 META-INF 中
@AutoService(Processor.class)
//指定解析的注解
@SupportedAnnotationTypes({"com.example.jzycc.lib_annotation.RouterUrl"})
public class RouterCompiler extends AbstractProcessor {
    private Filer filer;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //获取 RouterUrl 的集合
        Set< ? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(RouterUrl.class);

        //这里是 JavaPoet 的使用，作用是构建一个类，类名为 WhatRouterTable
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder("WhatRouterTable")
                .addModifiers(Modifier.PUBLIC);

        for (Element element : elements){
            //获取注解中的 url 信息
            String url = element.getAnnotation(RouterUrl.class).url();
            //将其专成存有类信息的 TypeElement
            TypeElement typeElement = (TypeElement)element;
            //获取目标类的全名
            String className = typeElement.getQualifiedName().toString();

            //构造一个字段，以 url 为名，类名为值，如 public final static url = "classname";
            FieldSpec fieldSpec = FieldSpec.builder(String.class,url)
                    .addModifiers(Modifier.PUBLIC,Modifier.FINAL,Modifier.STATIC)
                    .initializer("$S",className)
                    .build();
            //将字段添加到 typeSpec 中
            typeSpec.addField(fieldSpec);
        }
        try{
            //指定生成 Java 文件的 包名 （这个包现在还没建立，接下来它做为提供给开发者使用的 api）
            String packageFullName = "com.example.jzycc.lib_whatrouter";
            //构造 Java 文件
            JavaFile javaFile = JavaFile.builder(packageFullName, typeSpec.build()).build();
            //写入到 filer
            javaFile.writeTo(filer);

        }catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }
}
