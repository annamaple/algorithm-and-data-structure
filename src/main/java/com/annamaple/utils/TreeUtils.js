class TreeNode {
    constructor(val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

/**
 * @param {number[]} arr
 * @return {TreeNode}
 */
const createBinaryTree = function (arr) {
    if (arr == null || arr.length === 0) return null;
    let nodeQue = [];
    let root = new TreeNode(arr[0]);
    nodeQue.push(root);
    let index = 1;
    while (nodeQue.length > 0) {
        let size = nodeQue.length;
        for (let i = 0; i < size && index < arr.length; i++) {
            let node = nodeQue.shift();
            let left = new TreeNode(arr[index++]);
            let right = new TreeNode(arr[index++]);
            node.left = left;
            node.right = right;
            nodeQue.push(left);
            nodeQue.push(right);
        }
    }
    return root;
}
export {TreeNode, createBinaryTree};
