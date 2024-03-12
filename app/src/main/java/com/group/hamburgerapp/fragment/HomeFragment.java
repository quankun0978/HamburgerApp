package com.group.hamburgerapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group.hamburgerapp.R;
import com.group.hamburgerapp.adapter.ProductAdapter;
import com.group.hamburgerapp.database.ProductDatabase;
import com.group.hamburgerapp.entity.Product;
import com.group.hamburgerapp.entity.User;
import com.group.hamburgerapp.ultil.Ultils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private View view;

    private static RecyclerView rcvProduct;
    private static   ProductAdapter productAdapter;
    private static  List<Product> listProduct;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        handleGetProduct();
        return view;
    }
    void init(){
        rcvProduct = view.findViewById(R.id.rcv_product);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvProduct.setLayoutManager(layoutManager);
        // Inflate the layout for this fragment
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcvProduct.addItemDecoration(dividerItemDecoration);
        listProduct= ProductDatabase.getListProduct();
        productAdapter = new ProductAdapter(listProduct);
    }
    void handleGetProduct(){
        rcvProduct.setAdapter(productAdapter);
        ProductDatabase.getAllProduct(productAdapter);
    }
}