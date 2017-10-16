/*
 *   Created by zview@qq.com on 2017/10/15.
 *
 *
 *   复制一张图，先复制点，在复制边，这里注意就是可能会有环，注意增加判断
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null)
            return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        ArrayList<UndirectedGraphNode> nodes = new ArrayList<>();

        UndirectedGraphNode copyroot = new UndirectedGraphNode(node.label);
        map.put(node, copyroot);
        nodes.add(node);

        int index = 0;
        UndirectedGraphNode tmp, tmp1;
        while (index < nodes.size()) {
            tmp = nodes.get(index++);

            for (UndirectedGraphNode nodeneigh : tmp.neighbors) {
                if (!map.containsKey(nodeneigh)) {
                    nodes.add(nodeneigh);
                    tmp1=new UndirectedGraphNode(nodeneigh.label);
                    map.put(nodeneigh,tmp1);
                }
            }

        }

        for (UndirectedGraphNode n : nodes) {
            for (UndirectedGraphNode nn : n.neighbors) {
                map.get(n).neighbors.add(map.get(nn));
            }
        }
        return map.get(node);
    }


}

class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
};

