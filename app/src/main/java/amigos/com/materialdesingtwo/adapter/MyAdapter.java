package amigos.com.materialdesingtwo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import amigos.com.materialdesingtwo.R;
import amigos.com.materialdesingtwo.model.ModelFood;

/**
 * Created by sati on 11/11/2014.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ModelFood> foods;
    private Context mContext;

    public MyAdapter(List<ModelFood> foods, Context mContext) {
        this.foods = foods;
        this.mContext = mContext;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageViewFood;
        public TextView textViewTitleFood;
        public TextView textViewDescritionFood;


        public ViewHolder(View view) {
            super(view);
            imageViewFood = (ImageView) view.findViewById(R.id.imageViewFood);
            textViewTitleFood = (TextView) view.findViewById(R.id.textViewIitleFood);
            textViewDescritionFood = (TextView) view.findViewById(R.id.textViewDescritionFood);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext,foods.get(getPosition()).getTitleFood(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_textview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        ModelFood food = foods.get(i);
        viewHolder.textViewTitleFood.setText(food.getTitleFood());
        viewHolder.textViewDescritionFood.setText(food.getDescriptionFood());

        Picasso.with(mContext).load(food.getUrlImage()).into(viewHolder.imageViewFood);

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


}
