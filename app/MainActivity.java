package com.yashtourtravel.app;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razorpay.Checkout;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TourModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);
        Button payBtn = findViewById(R.id.payBtn);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseFirestore.getInstance()
                .collection("tours")
                .get()
                .addOnSuccessListener(q -> {
                    for (var d : q) {
                        list.add(d.toObject(TourModel.class));
                    }
                    recycler.setAdapter(new TourAdapter(list));
                });

        payBtn.setOnClickListener(v -> {
            Checkout checkout = new Checkout();
            checkout.setKeyID("RAZORPAY_KEY");

            try {
                JSONObject obj = new JSONObject();
                obj.put("name", "Yash Tour & Travel");
                obj.put("currency", "INR");
                obj.put("amount", "50000"); // ₹500
                checkout.open(this, obj);
            } catch (Exception e) {}
        });
    }
}
