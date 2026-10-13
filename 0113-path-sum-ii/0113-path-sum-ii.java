import java.util.*;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(root, targetSum, path, result);

        return result;
    }

    private void dfs(TreeNode node, int targetSum,
                     List<Integer> path,
                     List<List<Integer>> result) {

        if (node == null) {
            return;
        }

        path.add(node.val);
        targetSum -= node.val;

        // Check if current node is a leaf
        if (node.left == null && node.right == null && targetSum == 0) {
            result.add(new ArrayList<>(path));
        }

        // Explore left and right subtrees
        dfs(node.left, targetSum, path, result);
        dfs(node.right, targetSum, path, result);

        // Backtrack
        path.remove(path.size() - 1);
    }
}