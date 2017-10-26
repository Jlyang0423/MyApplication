package test.bwie.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import test.bwie.myapplication.bean.ShopLiBean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recycle;
    private List<ShopLiBean> list;
    private CheckBox tv_quanxuan;
    private ShopAdapter shopAdapter;
    private double sumPrice;
    private int sumNum;
    private TextView tv_sum;
    private TextView sum;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        inteDate();
        decimalFormat = new DecimalFormat("#0.00");
        recycle.setLayoutManager(new LinearLayoutManager(this));
        shopAdapter = new ShopAdapter(this,list);
        recycle.setAdapter(shopAdapter);
        shopAdapter.setOnItemChickListener(new ShopAdapter.OnItemChickListener() {
           @Override
           public void shopinter(int a, int b) {
               if (list.get(a).list.get(b).isSelect){
                   sumPrice=sumPrice+(list.get(a).list.get(b).price)*(list.get(a).list.get(b).text_sum);
                   sumNum=sumNum+list.get(a).list.get(b).text_sum;
               }else {
                   sumPrice=sumPrice-(list.get(a).list.get(b).price)*(list.get(a).list.get(b).text_sum);
                   sumNum= sumNum-list.get(a).list.get(b).text_sum;
               }
               tv_sum.setText(decimalFormat.format(sumPrice));
               sum.setText("去结算(" + sumNum + ")");
               isCheckAllCheck();
           }

        });
    }

    private void inteDate() {
        list=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
             List<ShopLiBean.DataBean> dataBean=new ArrayList<>();
            for (int j = 0; j <3 ; j++) {
                dataBean.add(new ShopLiBean.DataBean("商品"+j,10*(j+1),false,1));
            }
            list.add(new ShopLiBean("商家"+i,dataBean));
        }

    }
  private void  isCheckAllCheck(){
      boolean flag=true;
      for (ShopLiBean shopLiBean : list) {
          for (ShopLiBean.DataBean dataBean : shopLiBean.list) {
              if (!dataBean.isSelect){
                  flag=false;
                  break;
              }
          }
      }
    tv_quanxuan.setChecked(flag);

  }
    private void initView() {
        recycle = (RecyclerView) findViewById(R.id.shopcar_recycle);
        tv_quanxuan = (CheckBox) findViewById(R.id.checkbox_quanxuan);
        tv_quanxuan.setOnClickListener(this);
        tv_sum = (TextView) findViewById(R.id.tv_sum);
        sum = (TextView) findViewById(R.id.jiesuan_button);
    }

    @Override
    public void onClick(View view) {
        selectall();
    }

    private void selectall() {
        for (ShopLiBean shopLiBean : list) {
            for (ShopLiBean.DataBean dataBean : shopLiBean.list) {
                if (tv_quanxuan.isChecked()){
                    if (!dataBean.isSelect) {
                        dataBean.isSelect = true;
                        sumPrice=sumPrice+dataBean.price*dataBean.text_sum;
                        sumNum=sumNum+dataBean.text_sum;
                    }
                    }else {
                        if (dataBean.isSelect){
                            dataBean.isSelect=false;
                            sumPrice=sumPrice-dataBean.price*dataBean.text_sum;
                            sumNum=sumNum-dataBean.text_sum;
                        }
                    }
                }
            }

        shopAdapter.notifyDataSetChanged();
        tv_sum.setText(decimalFormat.format(sumPrice));
        sum.setText("去结算(" + sumNum + ")");
    }
}
