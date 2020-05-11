package java8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author wenda.zhuang
 * @Date 2020/4/28 12:43 PM
 * @Description ... E-mail   sis.nonacosa@gmail.com
 */
public class StreamTest {
	public static void main(String[] args) {

		Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9);
		stream.forEach(p -> System.out.println(p));

		System.out.println("===========");

		Stream<Integer> stream2 = Stream.of( new Integer[]{1,2,3,4,5,6,7,8,9} );
		stream2.forEach(p -> System.out.println(p));

		System.out.println("===========");

//		Stream<Date> stream3 = Stream.generate(() -> new Date());
//		stream3.forEach(p -> System.out.println(p));

		IntStream stream4 = "12345_abcdefg".chars();
		stream4.forEach(p -> System.out.println(p));

		//OR

		Stream<String> stream5 = Stream.of("A$B$C".split("\\$"));
		stream5.forEach(p -> System.out.println(p));

		System.out.println("=========");

		// list -> stream -> filter -> list
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i< 10; i++){
			list.add(i);
		}
		Stream<Integer> stream6 = list.stream();
		List<Integer> evenNumbersList = stream6.filter(i -> i%2 == 0).collect(Collectors.toList());
		System.out.print(evenNumbersList);





		// list -> stream -> filter -> arr
		List<Integer> list2 = new ArrayList<Integer>();
		for(int i = 1; i< 10; i++){
			list2.add(i);
		}

		Stream<Integer> stream7 = list2.stream();
		Integer[] evenNumbersArr2 = stream7.filter(i -> i%2 == 0).toArray(Integer[]::new);
		System.out.println(evenNumbersArr2);



		System.out.println("=========");

		List<String> memberNames = new ArrayList<>();
		memberNames.add("Amitabh");
		memberNames.add("Shekhar");
		memberNames.add("Aman");
		memberNames.add("Rahul");
		memberNames.add("Shahrukh");
		memberNames.add("Salman");
		memberNames.add("Yana");
		memberNames.add("Lokesh");

		memberNames.stream().filter((s) -> s.startsWith("A"))
				.map(String::toUpperCase)
				.forEach(System.out::println);

		System.out.println("=========");

		memberNames.stream().sorted()
				.map(String::toUpperCase)
				.forEach(System.out::println);

		System.out.println("=========");

		boolean matchedResult = memberNames.stream()
				.anyMatch((s) -> s.startsWith("A"));

		System.out.println(matchedResult);

		matchedResult = memberNames.stream()
				.allMatch((s) -> s.startsWith("A"));

		System.out.println(matchedResult);

		matchedResult = memberNames.stream()
				.noneMatch((s) -> s.startsWith("A"));

		System.out.println(matchedResult);



		System.out.println("=========");


		Optional<String> reduced = memberNames.stream()
				.reduce((s1,s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);


		System.out.println("=========");


		String firstMatchedName = memberNames.stream()
				.filter((s) -> s.startsWith("L"))
				.findFirst().get();

		System.out.println(firstMatchedName);




	}
}
