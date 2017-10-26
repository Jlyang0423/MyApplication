package test.bwie.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import test.bwie.myapplication.bean.ShopLiBean;
import test.bwie.myapplication.view.AddSubView;

/**
 * Created by 蒋六六 on 2017/10/25.
 */

public class ShopliAdapter extends RecyclerView.Adapter<ShopliAdapter.AAAAA> {
    private List<ShopLiBean.DataBean> list;
    private Context context;
    public OnItemCheckListener onItemCheckListener;
    public void setOnItemCheck(OnItemCheckListener onItemCheckListener){
        this.onItemCheckListener=onItemCheckListener;
    }
    public  ShopliAdapter(Context content, List<ShopLiBean.DataBean> list) {
        this.list=list;
        this.context=content;
    }

    @Override
    public AAAAA onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shopcar2_item, null);
        AAAAA adap=new AAAAA(inflate);
        return adap;
    }

    @Override
    public void onBindViewHolder(final AAAAA holder, final int position) {
        final ShopLiBean.DataBean dataBean = list.get(position);
        if (dataBean.isSelect){
            holder.ck.setChecked(true);
        }else {
            holder.ck.setChecked(false);
        }
        holder.num.setNum(list.get(position).text_sum+"");
        holder.tv_title.setText(dataBean.producrName);
        DecimalFormat decima=new DecimalFormat("#0.00");
        holder.tv.setText(decima.format(dataBean.price));
        if(onItemCheckListener!=null){
            holder.ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                     dataBean.isSelect=b;
                    onItemCheckListener.ok(holder.getLayoutPosition());
                }
            });
        }
        //自定义控件的赋值
        holder.num.setAddSubClickListener(new AddSubView.AddSubClickListener() {
            @Override
            public void addClickListener(int n) {
                  list.get(position).text_sum=n;
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class AAAAA extends RecyclerView.ViewHolder{
        private CheckBox ck;
        private TextView tv;
        private TextView tv_title;
        private AddSubView num;
        public AAAAA(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_jianjie);
            ck = itemView.findViewById(R.id.ck_gt);
            tv = itemView.findViewById(R.id.tv_shop_price);
            num = itemView.findViewById(R.id.nnn);
        }


    }

    public interface OnItemCheckListener{
        void ok(int pos);
    }

}
