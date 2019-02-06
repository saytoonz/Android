package com.nsromapa.android.tab2pakage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nsromapa.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAY on 10/08/2018.
 */

public class Tab2Fragment extends Fragment {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    List<Product> productList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container , false);

        productList = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.FriendsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.launcher));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_image));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.launcher));



        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.ic_dashboard_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_home_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.ic_search_black));

        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.launcher));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_image));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.launcher));



        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.ic_dashboard_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_home_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.ic_search_black));


        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.launcher));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_image));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.launcher));



        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.ic_dashboard_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_home_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.ic_search_black));


        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.launcher));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_image));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.launcher));



        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.ic_dashboard_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_home_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.ic_search_black));


        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.launcher));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_image));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.launcher));



        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.ic_dashboard_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_home_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.ic_search_black));


        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.launcher));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_image));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.launcher));



        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.ic_dashboard_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_home_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.ic_search_black));
        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.launcher));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_image));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.launcher));



        productList.add(
                new Product(
                        1,
                        "Apple notebook Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 Inch, 256 GB",
                        4.3,
                        60000,
                        R.drawable.ic_dashboard_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 Inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.ic_home_black_24dp));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 Inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.ic_search_black));


        ProductAdapter adapter = new ProductAdapter(getContext(),productList);

        recyclerView.setAdapter(adapter);


        return view;
    }
}
