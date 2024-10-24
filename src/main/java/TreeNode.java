import java.util.*;

class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            return true;
        }
        if(root1 != null && root2 == null) {
            return false;
        }
        if(root1 == null && root2 != null) {
            return false;
        }
        Deque<Node> q1 = new ArrayDeque<>();
        Deque<Node> q2 = new ArrayDeque<>();
        q1.offer(new Node(root1, -1));
        q2.offer(new Node(root2, -1));
        while(!q1.isEmpty() || !q2.isEmpty() ) {
            int size1 = q1.size();
            int size2 = q2.size();
            if(size1 != size2) {
                return false;
            }

            Map<Integer, Set<Integer>> map = new HashMap<>();
            for(int i = 0; i < size1; i++) {
                Node cur = q1.poll();
                TreeNode curTreeNode = cur.node;
                int parent = cur.parent;
                Set<Integer> set = map.getOrDefault(parent, new HashSet<>());
                set.add(curTreeNode.val);
                map.put(parent, set);
                if(curTreeNode.left != null) {
                    q1.offer(new Node(curTreeNode.left, curTreeNode.val));
                }
                if(curTreeNode.right != null) {
                    q1.offer(new Node(curTreeNode.right, curTreeNode.val));
                }
            }
            for(int i = 0; i < size2; i++) {
                Node cur2 = q2.poll();
                TreeNode curTreeNode = cur2.node;
                int parent = cur2.parent;
                if(map.containsKey(parent)){
                    if(!map.get(parent).contains(curTreeNode.val)) {
                        return false;
                    }
                }else return false;
                if(curTreeNode.left != null) {
                    q2.offer(new Node(curTreeNode.left, curTreeNode.val));
                }
                if(curTreeNode.right != null) {
                    q2.offer(new Node(curTreeNode.right, curTreeNode.val));
                }
            }


        }
        return true;
    }
}
class Node {
    TreeNode node;
    int parent;

    Node(TreeNode node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}