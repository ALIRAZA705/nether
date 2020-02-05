package com.monstertechno.loginsignupui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myholder extends RecyclerView.ViewHolder {
    TextView tx,tx2,tx3;
    public myholder(@NonNull View itemView) {
        super(itemView);
        this.tx= itemView.findViewById(R.id.tx1);
        this.tx2= itemView.findViewById(R.id.tx2);
//        this.tx3= itemView.findViewById(R.id.details);
    }
}
