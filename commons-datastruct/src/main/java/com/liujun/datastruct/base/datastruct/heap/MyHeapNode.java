package com.liujun.datastruct.base.datastruct.heap;

/**
 * @author liujun
 * @version 0.0.1
 * @date 2018/11/27
 */
public class MyHeapNode {

    /** 节点信息 */
    public class CodeNode {
        /** 编码存储的数据信息 */
        public char data;

        /** 字符出现的频率 */
        public int frequence;

        public CodeNode(char data, int frequence) {
            this.data = data;
            this.frequence = frequence;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CodeNode{");
            sb.append("data=").append(data);
            sb.append(", frequence=").append(frequence);
            sb.append('}');
            return sb.toString();
        }
    }

    /** 使用大顶堆用来存储数据对象信息 ,数组的下标从1开始，浪费一个字节的存储空间，可实现从0开始 */
    private final CodeNode[] data;

    /** 容量信息 */
    private final int capacity;

    /** 当前的大小信息 */
    private int length;

    /**
     * 通过构造函数来初始化堆信息
     *
     * <p>
     * 堆中三个重要的索引计算
     *
     * <p>
     * 1，如果当前节点的下标为n
     *
     * <p>
     * 那个父亲节点为n/2
     *
     * <p>
     * 左子节点为2*n
     *
     * <p>
     * 右子节点为2*n+1
     *
     * @param capacity
     *            容量信息
     */
    public MyHeapNode(int capacity) {
        this.capacity = capacity;
        this.data = new CodeNode[capacity];
    }

    /**
     * 进行数据插入操作
     *
     * @param value
     */
    public void insert(char dataInput, int value) {
        // 如果超过下标位置，则当前不再插入
        if (length > capacity) {
            return;
        }

        length++;
        // 将数据插入数组
        data[length] = new CodeNode(dataInput, value);

        int tmpIndex = length;

        CodeNode tmpVal;
        // 将数据进行交换，主要是当前与你父节点的对比
        while (tmpIndex / 2 > 0 && data[tmpIndex / 2].frequence < data[tmpIndex].frequence) {
            tmpVal = data[tmpIndex];
            data[tmpIndex] = data[tmpIndex / 2];
            data[tmpIndex / 2] = tmpVal;

            tmpIndex = tmpIndex / 2;
        }
    }

    /** 打印元素节点信息 */
    public void print() {
        for (int i = 0; i <= length; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println();
    }

    /** 删除堆顶元素 */
    public void deleteMax() {
        if (length <= 0) {
            return;
        }

        data[1] = data[length];
        length--;

        // 重新堆化，采用自顶向下的方法进行
        heap(data, length, 1);
    }

    private void heap(CodeNode[] data, int length, int start) {
        int tmpIndex;
        while (true) {
            tmpIndex = start;

            // 如果顶层节点的值小于左侧，则当前交换节点标识为左节点索引
            if (tmpIndex * 2 <= length && data[tmpIndex].frequence < data[tmpIndex * 2].frequence) {
                tmpIndex = tmpIndex * 2;
            }

            // 如果顶层节点小于右子树，则当前交换的节点的标为右子对索引
            if (tmpIndex * 2 <= tmpIndex && data[tmpIndex].frequence < data[tmpIndex * 2 + 1].frequence) {
                tmpIndex = tmpIndex * 2 + 1;
            }

            // 如果当前查找的节点，没有符号的情况，则退出
            if (tmpIndex == start) {
                break;
            }

            // 进行节点的交换操作
            CodeNode tmpValue = data[start];
            data[start] = data[tmpIndex];
            data[tmpIndex] = tmpValue;

            // 重新赋值为新的索引节点，进行遍历
            start = tmpIndex;
        }
    }

    public void createheap(CodeNode[] dataVal, int lengthtmp) {
        for (int i = lengthtmp / 2; i >= 1; i--) {
            heapFix(dataVal, lengthtmp, i);
        }
    }

    public void heapSort(CodeNode[] data, int length) {
        // 1,构造大顶堆
        createheap(data, length);

        int tmpIndex = length;

        while (tmpIndex > 1) {
            // 将当前节点与最后一个节点交换
            CodeNode tmpValue = data[tmpIndex];
            data[tmpIndex] = data[1];
            data[1] = tmpValue;

            tmpIndex--;

            // 构造第二大元素
            heapFix(data, tmpIndex, 1);
        }
    }

    /**
     * 使用大顶堆来构建
     *
     * @param data
     * @param length
     * @param start
     */
    public void heapFix(CodeNode[] data, int length, int start) {
        while (true) {
            int tmpIndex = start;

            // 如果左子树比父节点大，则标识为左路子节点交换
            if (start * 2 <= length && data[start].frequence < data[start * 2].frequence) {
                tmpIndex = start * 2;
            }

            // 如果右子树比当前比较的节点大，则标识为右子节点交换
            if (start * 2 + 1 <= length && data[tmpIndex].frequence < data[start * 2 + 1].frequence) {
                tmpIndex = start * 2 + 1;
            }

            // 如果未发生改变，则当前构建结束
            if (tmpIndex == start) {
                break;
            }

            // 进行节点交换操作
            CodeNode tmpValue = data[start];
            data[start] = data[tmpIndex];
            data[tmpIndex] = tmpValue;

            // 重新标识当前的开始节点
            start = tmpIndex;
        }
    }

    public static void buildHeap(CodeNode[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    private static void swap(CodeNode[] data, int start, int tmpIndex) {
        // 进行节点交换操作
        CodeNode tmpValue = data[start];
        data[start] = data[tmpIndex];
        data[tmpIndex] = tmpValue;
    }

    private static void heapify(CodeNode[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i].frequence < a[i * 2].frequence)
                maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos].frequence < a[i * 2 + 1].frequence)
                maxPos = i * 2 + 1;
            if (maxPos == i)
                break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }
}
