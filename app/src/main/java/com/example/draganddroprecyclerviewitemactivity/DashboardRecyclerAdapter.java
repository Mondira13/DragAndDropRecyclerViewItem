package com.example.draganddroprecyclerviewitemactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.draganddroprecyclerviewitemactivity.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardAdapterViewHolder> {


    private final Context context;
    private final List<Item> items;
    private String id;
    private  String title;


    public DashboardRecyclerAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DashboardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.recycle_layout, viewGroup, false);
        return new DashboardAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapterViewHolder dashboardAdapterViewHolder, int i) {
        id = items.get(i).getId();
        title = items.get(i).getName();
        String images = items.get(i).getImage();
        dashboardAdapterViewHolder.itemName.setText(title);
        Picasso.get().load(images).into(dashboardAdapterViewHolder.itemIcon); // use picasso image library

//        dashboardAdapterViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (id) {
//                    case "M1":
//                        Toast.makeText(context,title,Toast.LENGTH_SHORT).show();
//                        break;
//                    case "M2":
//                        Toast.makeText(context,title,Toast.LENGTH_SHORT).show();
//                        break;
//                    case "M3":
//                        Toast.makeText(context,title,Toast.LENGTH_SHORT).show();
//                        break;
//                    case "M4":
//                        Toast.makeText(context,title,Toast.LENGTH_SHORT).show();
//                        break;
//                        default:
//                            break;
//                }
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    class DashboardAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIcon;
        TextView itemName;
        LinearLayout parentLayout;

        public DashboardAdapterViewHolder(View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.itemIcon);
            itemName = itemView.findViewById(R.id.itemName);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }

    }
}
