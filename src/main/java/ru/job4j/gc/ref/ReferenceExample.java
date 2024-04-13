package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceExample {
    public static void main(String[] args) {
        StringBuilder strongStringBuilder = new StringBuilder("NEW BUILDER");
        SoftReference<StringBuilder> softReference = new SoftReference<>(strongStringBuilder);

        StringBuilder secondStringBuilder = new StringBuilder("SECOND BUILDER");
        WeakReference<StringBuilder> weakReference = new WeakReference<>(secondStringBuilder);


        StringBuilder softStringBuilder = softReference.get();
        if (softStringBuilder == null) {
            softReference = new SoftReference<>(strongStringBuilder);
            softStringBuilder = softReference.get();
        }
        softStringBuilder.append("SOFT Modified");


        StringBuilder weakStringBuilder = weakReference.get();
        if (weakStringBuilder == null) {
            weakReference = new WeakReference<>(secondStringBuilder);
            weakStringBuilder = weakReference.get();
        }

        weakStringBuilder.append("WEAK Modified");
    }
}
