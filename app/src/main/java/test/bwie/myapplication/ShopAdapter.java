package test.bwie.myapplication;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import test.bwie.myapplication.bean.ShopLiBean;

/**
 * Created by 蒋六六 on 2017/10/25.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder> {
    private Context content;
    private List<ShopLiBean> list;
    private OnItemChickListener onItemChickListener;
    public void setOnItemChickListener(OnItemChickListener onItemChickListener) {
        this.onItemChickListener = onItemChickListener;
    }
    public ShopAdapter(Context context, List<ShopLiBean> list) {
        this.content=context;
        this.list=list;
    }

    @Override
    public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View in = View.inflate(content, R.layout.shapcar1_item, null);
        ShopHolder shop=new ShopHolder(in);
        return shop;
    }
    @Override
    public void onBindViewHolder(final ShopHolder holder, final int position) {
        final ShopLiBean shopLiBean = list.get(position);
         ischeckshop(holder,shopLiBean);
        holder.tv_shophome.setText(shopLiBean.sellerName);
        holder.shop_recycle.setLayoutManager(new LinearLayoutManager(content));
      final   ShopliAdapter shopliadapter=new ShopliAdapter(content,shopLiBean.list);
        holder.shop_recycle.setAdapter(shopliadapter);

       if (onItemChickListener!=null){
           shopliadapter.setOnItemCheck(new ShopliAdapter.OnItemCheckListener() {
               @Override
               public void ok(int pos) {
                   onItemChickListener.shopinter(holder.getLayoutPosition(),pos);
                   ischeckshop(holder,shopLiBean);
               }

           });
           holder.ck_shophome.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   for (ShopLiBean.DataBean dataBean : shopLiBean.list) {
                       if (holder.ck_shophome.isChecked()){
                           dataBean.isSelect=true;
                       }else {
                           dataBean.isSelect=false;
                       }
                   }
                   shopliadapter.notifyDataSetChanged();
               }
           });
       }
    }
    private void ischeckshop(ShopHolder holder, ShopLiBean shopLiBean) {
        boolean f=true;
        for (ShopLiBean.DataBean dataBean : shopLiBean.list) {
            if (!dataBean.isSelect){
                f=false;
               break;
            }
        }
     holder.ck_shophome.setChecked(f);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ShopHolder extends RecyclerView.ViewHolder{
        private CheckBox ck_shophome;
        private TextView tv_shophome;
        private RecyclerView shop_recycle;
        public ShopHolder(View itemView) {
            super(itemView);
            ck_shophome = itemView.findViewById(R.id.ck_shophome);
            shop_recycle= itemView.findViewById(R.id.shop_li_recy);
            tv_shophome = itemView.findViewById(R.id.ck_shophome);
        }
    }

    public interface OnItemChickListener {
        void shopinter(int a, int b);

    }

}
