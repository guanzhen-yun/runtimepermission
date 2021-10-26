package com.inke.complier;

import com.google.auto.service.AutoService;
import com.inke.annotations.NeedsPermission;
import com.inke.annotations.OnNeverAskAgain;
import com.inke.annotations.OnPermissionDenied;
import com.inke.annotations.OnShowRetionale;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;

//通过auto-service中的@AutoService可以自动生成AutoService注解处理器是Google开发的。
// 用来生成META-INF/services/javax.annotation.processing.Processor文件
@AutoService(Processor.class)
// @SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PermissionProcessor extends AbstractProcessor {

    private Messager messager; // Messager用来报告错误，警告和其他提示信息
    private Elements elementUtils;// Elements中包含用于操作Elements的工具方法
    private Filer filer; //Filer用来创建新的源文件，class文件以及辅助文件
    private Types typeUtils; //Types中包含用于操作TypeMirror的工具方法

    //该方法主要用于一些初始化的操作，通过该方法的参数ProccessingEnvironment可以获取一些列有用的工具类
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnv);
        typeUtils = processingEnvironment.getTypeUtils();
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        // 添加支持注解的类型
        types.add(NeedsPermission.class.getCanonicalName());
        types.add(OnNeverAskAgain.class.getCanonicalName());
        types.add(OnPermissionDenied.class.getCanonicalName());
        types.add(OnShowRetionale.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        // 返回此注释 Processor支持的最新的源版本，该方法可以通过注解@SupportedSourceVersion指定
        return SourceVersion.latest();
    }

    //注解处理器的核心方法，处理具体的注解，生成Java文件
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        //获取NewMainActivity中所有带NeedsPermission注解的方法
        Set<? extends Element> needsPermissionSet = roundEnvironment.getElementsAnnotatedWith(NeedsPermission.class);
        // 保存键值对，key是com.inke.runtimepermission.newmain.NewMainActivity  value是所有带NeedsPermission注解的方法集合
        Map<String, List<ExecutableElement>> needsPermissionMap = new HashMap<>();
        //遍历所有带NeedsPermission注解的方法
        for (Element element : needsPermissionSet) {
            //转成原始属性元素（结构体元素）
            ExecutableElement executableElement = (ExecutableElement) element;
            //通过属性元素获取它所属的NewManiActivity类名，如:com.inke.runtimepermission.newmain.NewMainActivity
            String activityName = getActivityName(executableElement);
            //从缓存集合中获取NewMainActivity所有带NeedPermission注解的方法集合
            List<ExecutableElement> list = needsPermissionMap.get(activityName);
            if(list == null) {
                list = new ArrayList<>();
                //先加入map集合，引入变量list可以动态改变值
                needsPermissionMap.put(activityName, list);
            }
            //将NewMainActivity所有带NeedsPermission注解的方法加入到lisy集合
            list.add(executableElement);
            //测试打印：每个方法的名字
            System.out.println("NeedsPermission executableElement >>> " + executableElement.getSimpleName().toString());
        }

        //获取NewMainActivity中所有带OnNeverAskAgain注解的方法
        Set<? extends Element> onNeverAskAgainSet = roundEnvironment.getElementsAnnotatedWith(OnNeverAskAgain.class);
        Map<String, List<ExecutableElement>> onNeverAskAgainMap = new HashMap<>();
        for (Element element : onNeverAskAgainSet) {
            ExecutableElement executableElement = (ExecutableElement) element;
            String activityName = getActivityName(executableElement);
            List<ExecutableElement> list = onNeverAskAgainMap.get(activityName);
            if(list == null) {
                list = new ArrayList<>();
                onNeverAskAgainMap.put(activityName, list);
            }
            list.add(executableElement);
            System.out.println("executableElement >>> " + executableElement.getSimpleName().toString());
        }

        //获取NewMainActivity中所有带OnPermissionDenied注解的方法
        Set<? extends Element> onPermissionDeniedSet = roundEnvironment.getElementsAnnotatedWith(OnPermissionDenied.class);
        Map<String, List<ExecutableElement>> onPermissionDeniedMap = new HashMap<>();
        for (Element element : onPermissionDeniedSet) {
            ExecutableElement executableElement = (ExecutableElement) element;
            String activityName = getActivityName(executableElement);
            List<ExecutableElement> list = onPermissionDeniedMap.get(activityName);
            if(list == null) {
                list = new ArrayList<>();
                onPermissionDeniedMap.put(activityName, list);
            }
            list.add(executableElement);
            System.out.println("executableElement >>> " + executableElement.getSimpleName().toString());
        }

        //获取NewMainActivity中所有带OnShowRationale注解的方法
        Set<? extends Element> onShowRationaleSet = roundEnvironment.getElementsAnnotatedWith(OnShowRetionale.class);
        Map<String, List<ExecutableElement>> onShowRationaleMap = new HashMap<>();
        for (Element element : onShowRationaleSet) {
            ExecutableElement executableElement = (ExecutableElement) element;
            String activityName = getActivityName(executableElement);
            List<ExecutableElement> list = onShowRationaleMap.get(activityName);
            if(list == null) {
                list = new ArrayList<>();
                onShowRationaleMap.put(activityName, list);
            }
            list.add(executableElement);
            System.out.println("executableElement >>> " + executableElement.getSimpleName().toString());
        }

        //------------------------------造币过程-------------------------------------
        //获取Activity完整的字符串类名(包名 + 类名)
        for (String activityName : needsPermissionMap.keySet()) {
            //获取"com.inke.runtimepermission.newmain.NewMainActivity"中所有控件方法的集合
            List<ExecutableElement> needsPermissionElements = needsPermissionMap.get(activityName);
            List<ExecutableElement> onNeverAskAgainElements = onNeverAskAgainMap.get(activityName);
            List<ExecutableElement> onPermissionDeniedElements = onPermissionDeniedMap.get(activityName);
            List<ExecutableElement> onShowRationaleElements = onShowRationaleMap.get(activityName);

            final String CLASS_SUFFIX = "$Permissions";
            Filer filer = processingEnv.getFiler();
            try {
                //创建一个新的源文件(Class),并返回一个对象以允许写入它
                JavaFileObject javaFileObject = filer.createSourceFile(activityName + CLASS_SUFFIX);
                //通过方法标签获取包名标签（任意一个属性标签的父节点都是同一个包名）
                String packageName = getPackageName(needsPermissionElements.get(0));
                //定义Writer对象，开启造币过程
                Writer writer = javaFileObject.openWriter();

                //类名: NewMainActivity$Permissions，不是com.inke.runtimepermission.newmain.NewMainActivity$Permissions
                //通过属性元素获取它所属的NewMainActivity类名，再拼接后结果为:NewMainActivity$Permissions
                String activitySimpleName = needsPermissionElements.get(0).getEnclosingElement()
                        .getSimpleName().toString() + CLASS_SUFFIX;

                System.out.println("activityName >>> " + activityName + "\nactivitySimpleName >>> " + activitySimpleName);

                System.out.println("开始造币 ---------------------------->");
                //生成包
                writer.write("package " + packageName + ";\n");
                //生成要导入的接口类（必须手动导入）
                writer.write("import com.inke.library2.listener.RequestPermission;\n");
                writer.write("import com.inke.library2.listener.PermissionRequest;\n");
                writer.write("import com.inke.library2.utils.PermissionUtils;\n");
                writer.write("import androidx.appcompat.app.AppCompatActivity;\n");
                writer.write("import androidx.core.app.ActivityCompat;\n");
                writer.write("import androidx.annotation.NonNull;\n");
                writer.write("import java.lang.ref.WeakReference;\n");

                //生成类
                writer.write("public class " + activitySimpleName +
                        " implements RequestPermission<" + activityName + "> {\n");

                //生成常量属性
                writer.write("private static final int REQUEST_SHOWCAMERA = 666;\n");
                writer.write("private static String[] PERMISSION_SHOWCAMERA;\n");

                //生成requestPermission方法
                writer.write("public void requestPermission(" + activityName + " target, String[] permissions) {\n");

                writer.write("PERMISSION_SHOWCAMERA = permissions;\n");
                writer.write("if(PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCAMERA)) {\n");

                //循环生成NewMainActivity每个权限申请方法
                for (ExecutableElement executableElement : needsPermissionElements) {
                    //获取方法名
                    String methodName = executableElement.getSimpleName().toString();
                    // 调用申请权限方法
                    writer.write("target." + methodName + "();\n");
                }

                writer.write("} else if(PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {\n");

                //循环生成NewMainActivity每个提示用户为何要开启权限方法
                if(onShowRationaleElements != null && !onShowRationaleElements.isEmpty()) {
                    for (ExecutableElement executableElement : onShowRationaleElements) {
                        //获取方法名
                        String methodName = executableElement.getSimpleName().toString();
                        //调用提示用户为何要开启权限方法
                        writer.write("target." + methodName + "(new PermissionRequestImpl(target));\n");
                    }
                }

                writer.write("} else {\n");

                writer.write("  ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, 666);\n");
                writer.write("  }\n");
                writer.write("}\n");

                //....省略下面的生成

                writer.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return false;
    }

    private String getActivityName(ExecutableElement executableElement) {
        return executableElement.getClass().getSimpleName();
    }

    private String getPackageName(ExecutableElement executableElement) {
        return executableElement.getClass().getPackage().getName();
    }
}