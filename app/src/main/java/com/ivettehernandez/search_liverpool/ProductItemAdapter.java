    package com.ivettehernandez.search_liverpool;

    import android.content.Context;
    import android.support.v7.widget.CardView;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.animation.Animation;
    import android.view.animation.AnimationUtils;
    import android.widget.FrameLayout;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.ivettehernandez.search_liverpool.Utils.NumberFormat;
    import com.squareup.picasso.Picasso;

    import java.util.List;

    /**
     * Created by ivettelovegood on 2019-07-10.
     */


    public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.GoalViewHolder>{

        List<Product> products;
        Context context;
        private int lastPosition = -1;
        NumberFormat prizeformat;

        public ProductItemAdapter(List<Product> products, Context context){
            this.products = products;
            this.context = context;
            prizeformat = new NumberFormat();

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public GoalViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);

            GoalViewHolder goalViewHolder = new GoalViewHolder(v);

            return goalViewHolder;
        }

        @Override
        public void onBindViewHolder(GoalViewHolder personViewHolder, final int position) {
            personViewHolder.tv_item_producto_title.setText(products.get(position).titulo);
            personViewHolder.tv_item_producto_precio.setText(String.format("$ %s MXN", prizeformat.formatNumberMoney(products.get(position).precio)));

           Picasso.with(context).load(products.get(position).imagen).into(personViewHolder.imv_item_producto_thumb);
        }

        @Override
        public int getItemCount() {
            return products.size();
        }




        public static class GoalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            CardView cv;
            TextView tv_item_producto_title,
                    tv_item_producto_precio;

            ImageView imv_item_producto_thumb;

            FrameLayout container;

            GoalViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv_prdoducto_item);
                tv_item_producto_title     = (TextView) itemView.findViewById(R.id.tv_item_producto_title);
                tv_item_producto_precio = (TextView) itemView.findViewById(R.id.tv_item_producto_precio);
                imv_item_producto_thumb = (ImageView) itemView.findViewById(R.id.imv_item_producto_thumb);
                container          = (FrameLayout) itemView.findViewById(R.id.frame_item_container);

            }

            @Override
            public void onClick(View v) {

            }
        }


    }
