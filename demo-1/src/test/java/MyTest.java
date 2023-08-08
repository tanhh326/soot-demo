import org.junit.Test;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.JimpleBody;
import soot.options.Options;
import soot.toolkits.graph.DirectedGraph;
import soot.util.cfgcmd.CFGGraphType;
import soot.util.cfgcmd.CFGToDotGraph;
import soot.util.dot.DotGraph;

import java.util.Iterator;

public class MyTest {
    /**
     * jar包解压后就是一个目录(class目录)，可以直接进行soot的分析
     */
    @Test
    public void test1() {
        //指定要分析的类
        final String path = "target/classes";
        SootUtil.generalInitial(path);
        //获取类
        SootClass appclass = Scene.v().getSootClass("SootTest");
        //获取类中的相关的方法
        Iterator<SootMethod> methodIt = appclass.getMethods().iterator();
        int i = 0;
        while (methodIt.hasNext()) {
            //获取方法源
            JimpleBody body = (JimpleBody) methodIt.next().retrieveActiveBody();
            //option -> CompleteUnitGraph
            CFGGraphType graphtype = CFGGraphType.getGraphType("CompleteUnitGraph");
            //获取方法的控制流图
            DirectedGraph<Unit> graph = graphtype.buildGraph(body);
            //获取方法的控制流图的dot图
            CFGToDotGraph cfgToDotGraph = new CFGToDotGraph();
            graphtype.drawGraph(cfgToDotGraph, graph, body);
            DotGraph cfg = cfgToDotGraph.drawCFG(graph, body);
            //输出到文件
            cfg.plot("../dot-file/SootTest" + (++i) + ".dot");
        }
    }

    /**
     * 输出指定类型的分析结果
     */
    @Test
    public void test2() {
        final String path = "target/classes";
        SootUtil.outputInitial(path, Options.output_format_class);
        SootUtil.outputInitial(path, Options.output_format_jimple);
        SootUtil.outputInitial(path, Options.output_format_grimp);
        SootUtil.outputInitial(path, Options.output_format_baf);
    }
}
