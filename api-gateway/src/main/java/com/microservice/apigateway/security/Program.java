package com.microservice.apigateway.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.util.comparator.ComparableComparator;
import org.springframework.util.comparator.Comparators;

public class Program {
public static void main(String[] args) {
	List<Integer> asList = Arrays.asList(10,40,20,30,40,10);
	
	asList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
	.entrySet().stream().filter(t ->t.getValue()>1 )
	.forEach(System.out::println);
//	System.out.println(collect);
//	
	
	Entry<String, Integer> entry2 = Map.of("Saurav", 30000, "Shubham", 17000, "Vaibhav", 60000, "Sudip", 52000)
	.entrySet()
	.stream()
	 .sorted(Comparator.comparing(entry -> -entry.getValue())) // minus make it to do in desc order
     .toList()
     .get(1);
	
  List<Entry<String, Integer>> collect = Map.of("Saurav", 30000, "Shubham", 17000, "Vaibhav", 60000, "Sudip", 52000)
.entrySet()
.stream()
.max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
.stream()
.collect(Collectors.toList());

	System.out.println(collect);
	
	System.out.println(entry2);
	
	
//	.
	
	
}
}
