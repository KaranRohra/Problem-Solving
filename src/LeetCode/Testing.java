package LeetCode;

import java.util.*;

public class Testing {
    static int[][] dp;
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        //System.out.println(new Solution().canPartitionKSubsets(new int[]{2,2,2,2,3,4,5},4));
    }
}
class Solution {
    HashMap<TreeNode,Integer> map;
    int minIndex,maxIndex;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        map=new HashMap<>();

        minIndex=Integer.MAX_VALUE;
        maxIndex=Integer.MIN_VALUE;

        fillHashMap(root,0);

        return bfs(root);
    }
    public void fillHashMap(TreeNode root,int index){
        if(root==null)
            return;
        map.put(root,index);

        minIndex=Math.min(minIndex,index);
        maxIndex=Math.max(maxIndex,index);

        fillHashMap(root.left,index-1);
        fillHashMap(root.right,index+1);
    }
    public List<List<Integer>> bfs(TreeNode root){

        List<List<Integer>> ans=new ArrayList<>();

        for(int i=minIndex;i<=maxIndex;i++){
            ans.add(new ArrayList<>());
        }
        minIndex=-minIndex;

        Queue<TreeNode> q=new LinkedList<>();
        if(root!=null)
            q.offer(root);
        while (!q.isEmpty()){
            int n=q.size();
            HashMap<Integer,ArrayList<Integer>> temp=new HashMap<>();
            for(int i=0;i<n;i++){
                TreeNode p=q.poll();
                if(p!=null){
                    int index=map.get(p) + minIndex;
                    if (!temp.containsKey(index)) {
                        temp.put(index, new ArrayList<>());
                    }
                    temp.get(index).add(p.val);

                    if(p.left!=null)
                        q.offer(p.left);
                    if(p.right!=null)
                        q.offer(p.right);
                }
            }
            for(Map.Entry<Integer,ArrayList<Integer>> sortLists:temp.entrySet()){
                Collections.sort(sortLists.getValue());
                ans.get(sortLists.getKey()).addAll(sortLists.getValue());
            }
        }
        return ans;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public TreeNode makeTree(Integer ...a){
        return new TreeNode(1);
    }

    public void display() {

    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
