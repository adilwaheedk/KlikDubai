package com.khanstech.shop.klikdubai.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.activities.MainActivity;
import com.khanstech.shop.klikdubai.models.Constants;
import com.khanstech.shop.klikdubai.models.Product;
import com.khanstech.shop.klikdubai.utils.BitmapCacheHelper;
import com.khanstech.shop.klikdubai.utils.PhoneFunctionality;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ProductHorizontalList extends RecyclerView.Adapter<ProductHorizontalList.MyViewHolder> {

    private MainActivity mainActivity;
    private List<Product> list;
    private BitmapCacheHelper bitmapCacheHelper;

    public ProductHorizontalList(MainActivity mainActivity, List<Product> list) {
        try {
            this.mainActivity = mainActivity;
            this.list = list;
            bitmapCacheHelper = new BitmapCacheHelper();
        } catch (Exception ignored) {
            // Ignore because fragment is no longer visible
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_long, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Product obj = list.get(position);

        final String url = mainActivity.getString(R.string.url_product_img) + obj.product_id + "_1.jpg";
        Bitmap bitmap = bitmapCacheHelper.getBitmapFromMemCache(String.valueOf(position));
        if (bitmap == null) {
            new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... params) {
                    try {
                        return PhoneFunctionality.getBitmapFromURL(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                protected void onPostExecute(Bitmap bitmap) {
                    if (bitmap != null) {
                        bitmapCacheHelper.addBitmapToMemoryCache(String.valueOf(position), bitmap);
                        holder.item_img.setImageBitmap(bitmap);
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                            holder.item_img.setImageDrawable(mainActivity.getDrawable(R.drawable.img_na));
                        else
                            holder.item_img.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.img_na));
                    }
                }
            }.execute();

        } else {
            holder.item_img.setImageBitmap(bitmap);
            bitmapCacheHelper.addBitmapToMemoryCache(String.valueOf(position), bitmap);
        }
        if (obj.current_stock.equals("0")) {
            holder.item_out_of_stock.setVisibility(View.VISIBLE);
        } else {
            holder.item_out_of_stock.setVisibility(View.GONE);
        }
        holder.item_title.setText(obj.title);
        try {
            int sale_price = Integer.parseInt(obj.sale_price);
            int discount = Integer.parseInt(obj.discount);
            int new_price;
            if (obj.discount_type.equals("percent")) {
                new_price = sale_price * (discount / 100);
                holder.item_off.setText(obj.discount + "%");
            } else {
                new_price = sale_price - discount;
                holder.item_off.setText(Constants.APP_CURRENCY + obj.discount);
            }
            holder.item_price.setText(Constants.APP_CURRENCY + String.valueOf(new_price));
            holder.item_old_price.setText(Constants.APP_CURRENCY + String.valueOf(sale_price));
        } catch (NumberFormatException e) {
            holder.item_price.setText(Constants.APP_CURRENCY + obj.sale_price);
            holder.item_old_price.setVisibility(View.GONE);
            holder.item_off.setVisibility(View.GONE);
        }

        holder.item_rate.setVisibility(View.GONE);
        holder.item_vendor.setVisibility(View.GONE);

        holder.item_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneFunctionality.makeToast(mainActivity, "Add To Cart");
            }
        });
        holder.item_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneFunctionality.makeToast(mainActivity, "Add To Wishlist");
            }
        });
        holder.item_compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneFunctionality.makeToast(mainActivity, "Add To Compare List");
            }
        });
        holder.item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneFunctionality.makeToast(mainActivity, "Detail View");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        protected final ImageView item_img, item_add_cart, item_fav, item_compare, item_view;
        protected final TextView item_out_of_stock, item_off, item_title, item_price, item_old_price, item_vendor;
        protected final RatingBar item_rate;

        MyViewHolder(View v) {
            super(v);
            item_img = (ImageView) v.findViewById(R.id.item_img);
            item_out_of_stock = (TextView) v.findViewById(R.id.item_out_of_stock);
            item_off = (TextView) v.findViewById(R.id.item_off);
            item_title = (TextView) v.findViewById(R.id.item_title);
            item_price = (TextView) v.findViewById(R.id.item_price);
            item_old_price = (TextView) v.findViewById(R.id.item_old_price);
            item_rate = (RatingBar) v.findViewById(R.id.item_rate);
            item_vendor = (TextView) v.findViewById(R.id.item_vendor);
            item_add_cart = (ImageView) v.findViewById(R.id.item_add_cart);
            item_fav = (ImageView) v.findViewById(R.id.item_fav);
            item_compare = (ImageView) v.findViewById(R.id.item_compare);
            item_view = (ImageView) v.findViewById(R.id.item_view);
        }
    }
}
