import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.options.Options;

import java.util.Collections;

public class SootUtil {

    /**
     * 普通的初始化方式
     * @param path class目录
     */
   public static void generalInitial(String path) {
        soot.G.reset();
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_prepend_classpath(true);
        Options.v().set_validate(true);
        Options.v().set_src_prec(Options.src_prec_class);
        //源码class所在的目录
        Options.v().set_process_dir(Collections.singletonList(path));
        Options.v().set_whole_program(true);
        Options.v().set_no_bodies_for_excluded(true);
        Scene.v().addBasicClass("java.io.PrintStream", SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.lang.System", SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.lang.Thread", SootClass.SIGNATURES);
        Scene.v().loadNecessaryClasses();
    }

    /**
     * 指定输出形式的初始化方式
     * @param path class目录
     * @param outputMode 输出形式
     */
    public static void outputInitial(String path, int outputMode) {
        soot.G.reset();
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_prepend_classpath(true);
        Options.v().set_validate(true);
        //输出的形式（注意：如果开启了输出会获取不到源码）
        Options.v().set_output_format(outputMode);
        //输出的文件目录
        Options.v().set_output_dir("../SootOutput");
        //分析的源码类型
        Options.v().set_src_prec(Options.src_prec_class);
        //源码路径
        Options.v().set_process_dir(Collections.singletonList(path));
        Options.v().set_whole_program(true);
        Options.v().set_no_bodies_for_excluded(true);
        Scene.v().addBasicClass("java.io.PrintStream", SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.lang.System", SootClass.SIGNATURES);
        Scene.v().addBasicClass("java.lang.Thread", SootClass.SIGNATURES);
        Scene.v().loadNecessaryClasses();
        PackManager.v().writeOutput();
    }

}
