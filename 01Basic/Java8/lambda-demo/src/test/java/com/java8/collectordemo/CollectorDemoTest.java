package com.java8.collectordemo;

import com.java8.streamdemo.entity.DishEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class CollectorDemoTest {

    private final List<DishEntity> dishList = Arrays.asList(
            new DishEntity("番茄", true, 100, DishEntity.Type.蔬菜),
            new DishEntity("香蕉", false, 140, DishEntity.Type.水果),
            new DishEntity("鱼", false, 200, DishEntity.Type.海鲜),
            new DishEntity("牛肉", false, 700, DishEntity.Type.肉),
            new DishEntity("龙虾", false, 400, DishEntity.Type.海鲜),
            new DishEntity("鸡肉", false, 200, DishEntity.Type.肉),
            new DishEntity("兰花菜", true, 30, DishEntity.Type.蔬菜),
            new DishEntity("菜心", true, 40, DishEntity.Type.蔬菜)
    );

    /**
     * 分组
     */
    @Test
    void testCollectorGroup(){
        Optional.ofNullable(getGroupFunctionListByType(dishList)).ifPresent(System.out::println);
        System.out.println("----------------");
        Optional.ofNullable(getCollectorGroupListByType(dishList)).ifPresent(System.out::println);
    }

    private Map<DishEntity.Type, List<DishEntity>> getGroupFunctionListByType(List<DishEntity> list){
        Map<DishEntity.Type, List<DishEntity>> maps = new HashMap<>();
        list.stream().forEach(d -> {
            List<DishEntity> grpList = Optional.ofNullable(maps.get(d.getType())).orElseGet(() -> {
                List<DishEntity> newGrpList = new ArrayList<>();
                maps.put(d.getType(),newGrpList);
                return newGrpList;
            });
            grpList.add(d);
        });
        return maps;
    }

    private Map<DishEntity.Type,List<DishEntity>> getCollectorGroupListByType(List<DishEntity> list){
        return  list.stream().collect(Collectors.groupingBy(DishEntity::getType));
    }

    @Test
    void  testCollectors(){
        System.out.println("testAveragingDouble=");
        Optional.ofNullable(testAveragingDouble(dishList)).ifPresent(System.out::println);
        System.out.println("testAveragingInt=");
        Optional.ofNullable(testAveragingInt(dishList)).ifPresent(System.out::println);
        System.out.println("testAveragingLong=");
        Optional.ofNullable(testAveragingLong(dishList)).ifPresent(System.out::println);
        testCollectingAndThen(dishList).ifPresent(System.out::println);
        System.out.println("testGroupFunctionAndCollectors=");
        testGroupFunctionAndCollectors(dishList).ifPresent(System.out::println);
        System.out.println("testGroupFunctionAndSupplierAndCollectors=");
        testGroupFunctionAndSupplierAndCollectors(dishList).ifPresent(System.out::println);
    }

    // averagingDouble(ToDoubleFunction<? super T> mapper)
    private Double testAveragingDouble(List<DishEntity> list){
        Double result = list.stream().collect(Collectors.averagingDouble(DishEntity::getCalories));
        return  result;
    }

    private Double testAveragingInt(List<DishEntity> list){
        Double result = list.stream().collect(Collectors.averagingInt(DishEntity::getCalories));
        return  result;
    }

    private Double testAveragingLong(List<DishEntity> list){
        Double result = list.stream().collect(Collectors.averagingLong(DishEntity::getCalories));
        return  result;
    }

    private Optional<String> testCollectingAndThen(List<DishEntity> list){
        Optional<String> result = Optional.ofNullable(list.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(DishEntity::getCalories),f-> "testCollectingAndThen计算出来的平均数是：" + f)));
        return  result;
    }

    /**
     * 测试Collectors.collectingAndThen方法，支持返回的集合不允许给修改
     * @param list
     */
    private  void  testNotAllowToModifyReturnList(List<DishEntity> list){
        // 这样是直接允许修改
        // List<DishEntity> vegeList = list.stream().filter(d -> d.getType().equals(DishEntity.Type.蔬菜)).collect(Collectors.toList());
        // vegeList.add(new DishEntity("",false,10, DishEntity.Type.其他));
        List<DishEntity> vegeList = list.stream().filter(d -> d.getType().equals(DishEntity.Type.蔬菜)).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        // vegeList.add(new DishEntity("",false,10, DishEntity.Type.其他)); // 这时候修改会报错
    }

    private Optional<Map<DishEntity.Type, Long>> testGroupFunctionAndCollectors(List<DishEntity> list){
        Optional<Map<DishEntity.Type, Long>> result = Optional.ofNullable(list.stream().collect(Collectors.groupingBy(DishEntity::getType, Collectors.counting())));
        return result;
    }

    private Optional<Map<DishEntity.Type, Long>> testGroupFunctionAndSupplierAndCollectors(List<DishEntity> list){
        Map<DishEntity.Type, Long> result = list.stream().collect(Collectors.groupingBy(DishEntity::getType, TreeMap::new, Collectors.counting()));
        Optional.of(result.getClass()).ifPresent(System.out::println);
        return Optional.of(result);
    }

    // 继续学习Collector API
    @Test
    void  testCollectors2(){
        testGroupingByConcurrentWithFunction(dishList);
        testGroupingByConcurrentWithFunctionAndCollectors(dishList);
        testGroupingByConcurrentWithFunctionAndSupplierAndCollectors(dishList);
    }

    private void testGroupingByConcurrentWithFunction(List<DishEntity> list){
        System.out.println("testGroupingByConcurrentWithFunction=");
        ConcurrentMap<DishEntity.Type, List<DishEntity>> map = list.stream().collect(Collectors.groupingByConcurrent(DishEntity::getType));
        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private void testGroupingByConcurrentWithFunctionAndCollectors(List<DishEntity> list){
        System.out.println("testGroupingByConcurrentWithFunctionAndCollectors=");
        ConcurrentMap<DishEntity.Type, Double> map = list.stream().collect(Collectors.groupingByConcurrent(DishEntity::getType, Collectors.averagingDouble(DishEntity::getCalories)));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private void testGroupingByConcurrentWithFunctionAndSupplierAndCollectors(List<DishEntity> list){
        System.out.println("testGroupingByConcurrentWithFunctionAndSupplierAndCollectors=");
        ConcurrentMap<DishEntity.Type, Double> map = list.stream().collect(Collectors.groupingByConcurrent(DishEntity::getType, ConcurrentSkipListMap::new, Collectors.averagingDouble(DishEntity::getCalories)));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    // Collector练习3
    @Test
    void testCollectors3(){
        testCollectorsJoin(dishList);
        testCollectorsJoinWithDelimiter(dishList);
        testCollectorsJoinWithDelimiter2(dishList);
        testMapping(dishList);
        testMaxBy(dishList);
        testMinBy(dishList);
    }

    private void testCollectorsJoin(List<DishEntity> list){
        System.out.println("testCollectorsJoin");
        Optional.of(list.stream().map(DishEntity::getName).collect(Collectors.joining())).ifPresent(System.out::println);
    }

    private void testCollectorsJoinWithDelimiter(List<DishEntity> list){
        System.out.println("testCollectorsJoinWithDelimiter");
        Optional.of(list.stream().map(DishEntity::getName).collect(Collectors.joining(","))).ifPresent(System.out::println);
    }

    private void testCollectorsJoinWithDelimiter2(List<DishEntity> list){
        System.out.println("testCollectorsJoinWithDelimiter2");
        // 第一个参数为分隔符，第二个为前缀，第三个为后缀
        Optional.of(list.stream().map(DishEntity::getName).collect(Collectors.joining(",","a-","-b"))).ifPresent(System.out::println);
    }

    private void  testMapping(List<DishEntity> list){
        System.out.println("testMapping");
        Optional.ofNullable(list.stream().collect(Collectors.mapping(DishEntity::getName, Collectors.joining(",")))).ifPresent(System.out::println);
    }

    private void  testMaxBy(List<DishEntity> list){
        System.out.println("testMaxBy");
        Optional.ofNullable(list.stream().collect(Collectors.maxBy(Comparator.comparing(DishEntity::getCalories)))).ifPresent(System.out::println);
    }

    private void  testMinBy(List<DishEntity> list){
        System.out.println("testMinBy");
        Optional.ofNullable(list.stream().collect(Collectors.minBy(Comparator.comparing(DishEntity::getCalories)))).ifPresent(System.out::println);
    }

    @Test
    void testCollector4(){
        testPartitioningByWithPredicate(dishList);
        testPartitioningByWithPredicateAndCollector(dishList);
        testReducing(dishList);
        testReducingWithOperatorAndIdentity(dishList);
        testReducingWithOperatorAndIdentityAndFunction(dishList);
        testSummarizingDouble(dishList);
    }

    // partitioningBy
    private void  testPartitioningByWithPredicate(List<DishEntity> list){
        System.out.println("testPartitioningByWithPredicate");
        Map<Boolean, List<DishEntity>> vegeMap = list.stream().collect(Collectors.partitioningBy(DishEntity::isVegetarian));
        Optional.ofNullable(vegeMap).ifPresent(System.out::println);
    }

    private void  testPartitioningByWithPredicateAndCollector(List<DishEntity> list){
        System.out.println("testPartitioningByWithPredicateAndCollector");
        Map<Boolean, Double> map = list.stream().collect(Collectors.partitioningBy(DishEntity::isVegetarian, Collectors.averagingDouble(DishEntity::getCalories)));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    /**
     * reducing
     */
    private void  testReducing(List<DishEntity> list){
        System.out.println("testReducing");
        Optional<DishEntity> optional = list.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(DishEntity::getCalories))));
        optional.ifPresent(System.out::println);
    }

    private  void testReducingWithOperatorAndIdentity(List<DishEntity> list){
        System.out.println("testReducingWithOperatorAndIdentity");
        Integer result = list.stream().map(DishEntity::getCalories).collect(Collectors.reducing(0, (a, b) -> a + b));
        System.out.println(result);
    }

    private  void testReducingWithOperatorAndIdentityAndFunction(List<DishEntity> list){
        System.out.println("testReducingWithOperatorAndIdentityAndFunction");
        Integer result = list.stream().collect(Collectors.reducing(0,DishEntity::getCalories, (a, b) -> a + b));
        System.out.println(result);
    }

    // summarize
    private void testSummarizingDouble(List<DishEntity> list){
        System.out.println("testSummarizingDouble");
        DoubleSummaryStatistics result = list.stream().collect(Collectors.summarizingDouble(DishEntity::getCalories));
        System.out.println(result.getMin());
        System.out.println(result.getMax());
        System.out.println(result.getAverage());
        System.out.println(result.getCount());
        Optional.ofNullable(result).ifPresent(System.out::println);

    }

    @Test
    void  testCollector5(){
        testToCollection(dishList);
        testToConcurrentMap(dishList);
        testToConcurrentMapAndSupplier(dishList);
        testToConcurrentMapAndOperatorAndSupplier(dishList);
    }

    //toCollection
    private void testToCollection(List<DishEntity> list){
        System.out.println("testToCollection");
        LinkedList<DishEntity> linkList = list.stream().filter(p -> p.getCalories() > 500).collect(Collectors.toCollection(LinkedList::new));
        Optional.ofNullable(linkList.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(linkList).ifPresent(System.out::println);
    }

    // toConcurrentMap
    private void testToConcurrentMap(List<DishEntity> list){
        System.out.println("testToConcurrentMap");
        ConcurrentMap<String, Integer> map = list.stream().collect(Collectors.toConcurrentMap(DishEntity::getName,DishEntity::getCalories));
        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(map).ifPresent(System.out::println);

    }

    private void testToConcurrentMapAndSupplier(List<DishEntity> list){
        System.out.println("testToConcurrentMapAndSupplier");
        ConcurrentMap<DishEntity.Type, Long> map = list.stream().collect(Collectors.toConcurrentMap(DishEntity::getType, v->2L, (a, b) -> a + b));
        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(map).ifPresent(System.out::println);

    }

    private void testToConcurrentMapAndOperatorAndSupplier(List<DishEntity> list){
        System.out.println("testToConcurrentMapAndOperatorAndSupplier");
        ConcurrentSkipListMap<DishEntity.Type, Long> map = list.stream().collect(Collectors.toConcurrentMap(DishEntity::getType, v -> 2L, (a, b) -> a + b, ConcurrentSkipListMap::new));
        Optional.ofNullable(map).ifPresent(v -> {
            System.out.println(v.getClass());
            System.out.println(v);
        });
    }

}
