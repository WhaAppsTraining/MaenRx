package sembarang.maenrx;

import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

/**
 * @author hendrawd on 24/06/18
 */
public class OtherJavaExamples {

    OtherJavaExamples() {
        // Yang paling sering dipakai di java 8 adalah lambda expression dan method reference
        // masih banyak fungsi java 8 yang membutuhkan API level 24, jadi kalau misal app kita
        // target min SDK 14, maka akan error

        String[] strings = {"one", "two", "three", "four", "five"};
        List<String> stringList = Arrays.asList(strings);

        // consumer pattern(action yang bisa dilakukan untuk tiap2 item)
        // stringList.forEach(s -> System.out.println(s));

        // method reference
        // stringList.forEach(System.out::println);
        // java8
        methodThatNeedRunnable(this::doSomething);
        // java7
        // methodThatNeedRunnable(new Runnable() {
        //     @Override
        //     public void run() {
        //         doSomething();
        //     }
        // });

        // stream
        // Stream<String> stringStream = Stream.of(strings);
    }

    private void doSomething() {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Timber.d("method %s dipanggil", methodName);
    }

    private void methodThatNeedRunnable(Runnable task) {
        new Thread(task).start();
    }
}
