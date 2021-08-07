package xyz.akashchaudhary.covid_19tracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;

import xyz.akashchaudhary.covid_19tracker.api.CountryData;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context context;
    private List<CountryData> list;

    public CountryAdapter(Context context, List<CountryData> list) {
        this.context = context;
        this.list = list;
    }
    
    public void filterList(List<CountryData> filterList){
        list = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_item_layout, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CountryAdapter.CountryViewHolder holder, int position) {
        CountryData data = list.get(position);
        holder.countryCases.setText(NumberFormat.getInstance().format(Integer.parseInt(data.getTotalCases())));
        holder.countryName.setText(data.getCountryName());
        holder.srno.setText(String.valueOf(position + 1));
        
        Glide.with(context).load(data.getCountryInfo().get("flag")).into(holder.countryImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("country", data.getCountryName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView srno, countryName, countryCases;
        private ImageView countryImage;

        public CountryViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            srno = itemView.findViewById(R.id.text_srno);
            countryName = itemView.findViewById(R.id.text_countryName);
            countryCases = itemView.findViewById(R.id.text_countryCases);
            countryImage = itemView.findViewById(R.id.img_country_flag);
        }
    }
}
