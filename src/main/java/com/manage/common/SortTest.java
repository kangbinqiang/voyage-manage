package com.manage.common;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class SortTest {


    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     *
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {
        int minIndex, temp;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }


    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        int preIndex, current;
        for (int i = 1; i < arr.length; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }


    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        int len = arr.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = temp;
            }
            gap = gap / 2;
        }
        return arr;
    }


    /**
     * 归并排序
     *
     * @param arr
     * @return
     */
    public static int[] MergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(MergeSort(left), MergeSort(right));
    }


    /**
     * 有序数组的合并
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            //i为left的数组下标，j为right的数组下标
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }


    /**
     * 快速排序
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int[] quickSort(int[] arr, int start, int end) {
        if (arr.length < 1 || start < 0 || end > arr.length || start > end) {
            return null;
        }
        int smallIndex = partition(arr, start, end);
        if (smallIndex > start) {
            quickSort(arr, start, smallIndex - 1);
        }
        if (smallIndex < end) {
            quickSort(arr, smallIndex + 1, end);
        }
        return arr;
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(arr, pivot, end);
        for (int i = start; i <= end; i++) {
            if (arr[i] <= arr[end]) {
                smallIndex++;
                if (i > smallIndex) {
                    swap(arr, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 堆排序
     *
     * @param arr
     * @return
     */
    public static int[] heapSort(int[] arr) {
        if (arr.length < 1) {
            return null;
        }
        buildMaxHeap(arr);
        int len = arr.length;
        while (len > 0) {
            swap(arr, 0, len - 1);
            len--;
            adjustHeap(arr, 0);
        }
        return arr;
    }

    public static void buildMaxHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            adjustHeap(arr, i);
        }
    }

    public static void adjustHeap(int[] arr, int i) {
        int len = arr.length;
        int maxIndex = i;
        //如果有左子树，且左子树大于根节点，则将最大指针指向左子树
        if (2 * i < len && arr[2 * i] > arr[maxIndex]) {
            maxIndex = 2 * i;
        }
        //如果有右子树，且右子树大于根节点，家则将最大指针指向右子树
        if (2 * i + 1 < len && arr[2 * i + 1] > arr[maxIndex]) {
            maxIndex = 2 * i + 1;
        }
        //如果父节点不是最大值，则父节点与最大值交换，并调整树
        if (maxIndex != i) {
            swap(arr, maxIndex, i);
            adjustHeap(arr, maxIndex);
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 54, 1, 6, 8, 0, 3};
//        arr = bubbleSort(arr);
//        arr = selectionSort(arr);
//        arr = insertSort(arr);
//        arr = shellSort(arr);
//        arr = MergeSort(arr);
//        arr = quickSort(arr, 0, 7);
        arr = heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    static class TreeNode {
        public int data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }


    /**
     * 创建二叉树
     * @param list
     * @return
     */
    public static TreeNode createTree(LinkedList<Integer> list) {
        TreeNode treeNode = null;
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        Integer data = list.removeFirst();
        if (data != null) {
            treeNode = new TreeNode(data);
            treeNode.left = createTree(list);
            treeNode.right = createTree(list);
        }
        return treeNode;
    }


    /**
     * 前序遍历
     * @param node
     */
    public static void preOrderTravel(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrderTravel(node.left);
        preOrderTravel(node.right);
    }


    /**
     * 合营多个链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
