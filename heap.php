<?php

class Heap{
	private $value = array();
	private $size;
	
	public function __construct($value){
		$this->value = $value;
		$this->size = count($value)-1;
	}
	
	public function remove_head(){
		if($this->size == 0) return null;
		
		$head = $this->value[1];
		$this->value[1] = $this->value[$this->size];
		unset($this->value[$this->size]);
		$this->size--;				
		$this->sink_down(1);
		
		return $head;
	}
	
	public function insert($new_value){
		$this->size++;
		$this->value[] = $new_value;
		$this->float_up($this->size);
	}
	
	public function print_heap(){
		if($this->size == 0) print "Heap is empty.\n";
		else{
			print_r($this->value);
		}
	}
	
	private function sink_down($index){
		$parent = $index;
		$child = 2 * $index;
		while($child <= $this->size){
			if($child + 1 <= $this->size && $this->value[$child] < $this->value[$child+1])
				$child = $child + 1;
			if($this->value[$parent] < $this->value[$child]){
				$temp = $this->value[$parent];
				$this->value[$parent] = $this->value[$child];
				$this->value[$child] = $temp; 
				$parent = $child;
				$child *= 2;
			}
			else
				break;
		}
	}
	
	private function float_up($index){
		$child = $index;
		$parent = $child / 2;
		while($parent >= 1){
			if($this->value[$child] > $this->value[$parent]){
				$temp = $this->value[$parent];
				$this->value[$parent] = $this->value[$child];
				$this->value[$child] = $temp; 
				$child = $parent;
				$parent = $child / 2;
			}
			else
				break;
		}
	}
}

$heap = new Heap(array(null,10));
print "Heap is:\n";
$heap->print_heap();
/*$head = $heap->remove_head();
print "Head is: $head\n";
print "After removing head: \n";
$heap->print_heap();*/
$heap->insert(9);
print "After inserting: \n";
$heap->print_heap();
