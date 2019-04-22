package com.nmssdmf.testmodule.designmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.designmode.factory.Shape;
import com.nmssdmf.testmodule.designmode.factory.ShapeFactory;
import com.nmssdmf.testmodule.designmode.single.Singleton_DCL;
import com.nmssdmf.testmodule.designmode.single.Singleton_Enum;
import com.nmssdmf.testmodule.designmode.single.Singleton_Hungry;
import com.nmssdmf.testmodule.designmode.single.Singleton_Inner;
import com.nmssdmf.testmodule.designmode.single.Singleton_Lazy;

public class DesignModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_mode);

        //单例模式
        findViewById(R.id.btnSingleton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton_DCL singleton_dcl = Singleton_DCL.getInstance();
                singleton_dcl.printName();

                Singleton_Enum singleton_enum = Singleton_Enum.INSTANCE;
                singleton_enum.printName();

                Singleton_Hungry singleton_hungry = Singleton_Hungry.getInstance();
                singleton_hungry.printName();

                Singleton_Inner singleton_inner = Singleton_Inner.getInstance();
                singleton_inner.printName();

                Singleton_Lazy singleton_lazy = Singleton_Lazy.getInstance();
                singleton_lazy.printName();
            }
        });
        //Builder模式
        findViewById(R.id.btnBuilder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //原型模式
        findViewById(R.id.btnPrototype).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //工厂模式
        findViewById(R.id.btnFactory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShapeFactory shapeFactory = new ShapeFactory();

                Shape square = shapeFactory.getShape("square");
                square.draw();

                Shape rectangle = shapeFactory.getShape("rectangle");
                rectangle.draw();
            }
        });
        //抽象工厂模式
        findViewById(R.id.btnAbstractFactory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //策略模式
        findViewById(R.id.btnStrategy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //状态模式
        findViewById(R.id.btnState).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //责任链模式
        findViewById(R.id.btnChain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //解释器模式
        findViewById(R.id.btnInterpreter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //命令模式
        findViewById(R.id.btnCommand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //观察者模式
        findViewById(R.id.btnObserver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //备忘录模式
        findViewById(R.id.btnMemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //迭代器模式
        findViewById(R.id.btnIterator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //模板方法模式
        findViewById(R.id.btnTemplateMethod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //访问者模式
        findViewById(R.id.btnVisitor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //中介模式
        findViewById(R.id.btnMediator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //代理模式
        findViewById(R.id.btnProxy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //组合模式
        findViewById(R.id.btnCombined).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //适配器模式
        findViewById(R.id.btnAdaptation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //装饰模式
        findViewById(R.id.btnDecorative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //享元模式
        findViewById(R.id.btnFlyweight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //外观模式
        findViewById(R.id.btnFacade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //桥接模式
        findViewById(R.id.btnBridge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
