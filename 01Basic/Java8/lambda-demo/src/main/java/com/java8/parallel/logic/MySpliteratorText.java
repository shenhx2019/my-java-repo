package com.java8.parallel.logic;

import com.java8.utils.SimpleConsoleLog;

import java.sql.Statement;
import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author shenhx
 */
public class MySpliteratorText {
    private final String[] data;

    public MySpliteratorText(String text) {
        Objects.requireNonNull(text, "参数不允许为空");
        this.data = text.split("\n");
    }

    public Stream<String> stream(){
        return StreamSupport.stream(new InnerSpliterator(), false);
    }

    public Stream<String> parallelStream(){
        return StreamSupport.stream(new InnerSpliterator(),true);
    }

    private class InnerSpliterator implements Spliterator<String>{

        private int start,end;

        public InnerSpliterator() {
            this.start = start=0;
            this.end = end=MySpliteratorText.this.data.length-1;
        }

        public InnerSpliterator(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean tryAdvance(Consumer<? super String> consumer) {
            SimpleConsoleLog.writeDebugLog("tryAdvance");
            if(start <= end){
                consumer.accept(MySpliteratorText.this.data[start++]);
                return true;
            }
            return false;
        }
        @Override
        public Spliterator<String> trySplit() {
            SimpleConsoleLog.writeDebugLog("trySplit");
            int mid = (end - start);
            if(mid <= 1){
                return  null;
            }
            int left = start;
            int right = mid+start;
            start = mid + start + 1;
            return  new InnerSpliterator(left,right);
        }

        @Override
        public long estimateSize() {
            SimpleConsoleLog.writeDebugLog("estimateSize");
            return end - start;
        }

        @Override
        public long getExactSizeIfKnown() {
            SimpleConsoleLog.writeDebugLog("getExactSizeIfKnown");
            return estimateSize();
        }

        @Override
        public int characteristics() {
            SimpleConsoleLog.writeDebugLog("characteristics");
            return IMMUTABLE | SIZED | SUBSIZED;
        }
    }

}
