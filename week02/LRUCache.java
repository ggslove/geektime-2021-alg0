class LRUCache {

  static class ListNode {
    int key;
    int value;
    ListNode prev;
    ListNode next;

    public ListNode(int key, int value) {
      this.key = key;
      this.value = value;
    }

    public ListNode() {}
  }

  private Map<Integer, ListNode> map;
  private ListNode head; //有保护节点 就很好写，要不然就要处理head的变化
  private ListNode tail;

  private int capacity;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    map = new HashMap();
    head = new ListNode();
    tail = new ListNode();
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    if (this.map.containsKey(key)) {
      ListNode node = this.map.get(key);
      // 删除node位置
      node.prev.next = node.next;
      node.next.prev = node.prev;
      // 放到head后面
      node.next = head.next;
      node.prev = head;
      head.next.prev = node;
      head.next = node;
      // System.out.println("get后打印:"+key);
      // print();
      return node.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    //System.out.println("------------put--------"+key);
    ListNode node = null;
    if (!this.map.containsKey(key)) {
      node = new ListNode(key, value);
      this.map.put(key, node); //这里预先会插入一个
      if (this.map.size() == 0) {
        //保护节点处理
        node.next = tail;
        tail.prev = node;
        head.next = node;
        node.prev = head;
        return;
      }
      if (this.map.size() == this.capacity + 1) {
        // System.out.println("this.map.size():"+this.map.size()
        // +",this.capacity"+this.capacity);
        // 满了
        // 删除最后一个
        this.map.remove(tail.prev.key);
        //System.out.println("删除了"+tail.prev.key);
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
      }
    } else {
      node = this.map.get(key);
      node.value = value;
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }
    // 放到head后面
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
    // print();
  }

  public void print() {
    System.out.println("=========> 打印开始");
    ListNode node = head;
    while (node != null) {
      System.out.println(node.value);
      node = node.next;
    }
    System.out.println("=========> 打印结束");
  }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
