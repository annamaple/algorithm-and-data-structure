/**
 * Definition for a binary tree node.
 * function TreeUtils(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
import {createBinaryTree, TreeNode} from "../../../utils/TreeUtils";

/**
 * @param {TreeNode} root
 * @return {number[][]}
 */
const zigzagLevelOrder = function(root) {
    let res =  [];
    if (root == null) return res;
    let nodeQue = [];
    nodeQue.unshift(root);
    let isOrderLeft = true;
    while (nodeQue.length > 0) {
        let size = nodeQue.length;
        let deQueList = [];
        for (let i = 0; i < size; i++) {
            let node = nodeQue.pop();
            if (isOrderLeft) {
                deQueList.push(node.val);
            } else {
                deQueList.unshift(node.val);
            }
            if (node.left != null) {
                nodeQue.unshift(node.left);
            }
            if (node.right != null) {
                nodeQue.unshift(node.right);
            }
        }
        isOrderLeft = !isOrderLeft;
        res.push(new Array(deQueList));
    }
    return res;
};

let arr = [3,9,20,null,null,15,7];
console.log(zigzagLevelOrder(createBinaryTree(arr)));

