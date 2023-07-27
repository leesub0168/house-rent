package com.org.houserent.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;

import java.util.List;

public class JpaItemListWriter<T> extends JpaItemWriter<List<T>> {

    private JpaItemWriter<T> jpaItemWriter;

    public JpaItemListWriter(JpaItemWriter<T> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(Chunk<? extends List<T>> items) {
        Chunk<T> totalList = new Chunk<T>();

        for (List<T> list : items) {
            totalList.addAll(list);
        }
        jpaItemWriter.write(totalList);
    }
}
