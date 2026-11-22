package com.nad.ffaimtool;

import android.app.*;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    final int BG=Color.rgb(9,9,10), CARD=Color.rgb(24,23,20), GOLD=Color.rgb(214,180,90), WHITE=Color.WHITE, MUTED=Color.rgb(175,168,150);
    LinearLayout root;
    android.content.SharedPreferences prefs;

    GradientDrawable shape(int color, int stroke) {
        GradientDrawable d=new GradientDrawable(); d.setColor(color); d.setCornerRadius(22);
        if(stroke!=0) d.setStroke(2,stroke); return d;
    }
    TextView text(String s,int size,int color,boolean bold) {
        TextView t=new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(color);
        if(bold)t.setTypeface(Typeface.DEFAULT,Typeface.BOLD); t.setPadding(0,4,0,4); return t;
    }
    void addCard(String title,String desc) {
        LinearLayout c=new LinearLayout(this); c.setOrientation(1); c.setPadding(18,14,18,14);
        c.setBackground(shape(CARD,Color.rgb(91,73,35)));
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(0,0,0,14); root.addView(c,p);
        c.addView(text(title,18,GOLD,true)); c.addView(text(desc,14,MUTED,false));
    }
    void section(String title,String desc) { addCard(title,desc); }
    void addSlider(String label,int initial,String key) {
        LinearLayout c=new LinearLayout(this); c.setOrientation(1); c.setPadding(18,12,18,12); c.setBackground(shape(CARD,0));
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(0,0,0,10); root.addView(c,p);
        TextView v=text(label+" : "+initial,15,WHITE,true); c.addView(v);
        SeekBar sb=new SeekBar(this); sb.setMax(200); sb.setProgress(initial); sb.setProgressTintList(android.content.res.ColorStateList.valueOf(GOLD));
        sb.setThumbTintList(android.content.res.ColorStateList.valueOf(GOLD)); c.addView(sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onProgressChanged(SeekBar b,int n,boolean u){v.setText(label+" : "+n);}
            public void onStartTrackingTouch(SeekBar b){}
            public void onStopTrackingTouch(SeekBar b){prefs.edit().putInt(key,b.getProgress()).apply();}
        });
    }
    void button(String label,Runnable action) {
        Button b=new Button(this); b.setText(label); b.setTextColor(Color.BLACK); b.setAllCaps(false); b.setTypeface(null,Typeface.BOLD);
        b.setBackground(shape(GOLD,0)); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(0,4,0,12); root.addView(b,p);
        b.setOnClickListener(v->action.run());
    }
    @Override public void onCreate(Bundle b) {
        super.onCreate(b); prefs=getSharedPreferences("nad_settings",MODE_PRIVATE);
        ScrollView scroll=new ScrollView(this); scroll.setBackgroundColor(BG);
        root=new LinearLayout(this); root.setOrientation(1); root.setPadding(20,22,20,24); scroll.addView(root); setContentView(scroll);
        TextView brand=text("NAD  •  FF AIM TOOL",25,GOLD,true); root.addView(brand);
        root.addView(text("PREMIUM EDITION  |  BLACK & GOLD",11,MUTED,true));
        section("AIM TRAINING","Alat latihan dan panduan setting. Tidak mengubah file Free Fire, tidak melakukan auto-aim, dan tidak menjamin headshot otomatis.");
        section("SENSITIVITY PRESET","Mulai dari nilai rekomendasi berikut, lalu sesuaikan dengan kenyamanan dan perangkat. Nilai di game dapat berbeda.");
        addSlider("General",prefs.getInt("general",95),"general");
        addSlider("Red Dot",prefs.getInt("reddot",90),"reddot");
        addSlider("2x Scope",prefs.getInt("scope2",85),"scope2");
        addSlider("4x Scope",prefs.getInt("scope4",80),"scope4");
        addSlider("Sniper Scope",prefs.getInt("sniper",50),"sniper");
        addSlider("Free Look",prefs.getInt("free",75),"free");
        button("SIMPAN PRESET",()->Toast.makeText(this,"Preset tersimpan di perangkat!",Toast.LENGTH_SHORT).show());
        button("PULIHKAN REKOMENDASI",()->{
            int[] vals={95,90,85,80,50,75}; String[] keys={"general","reddot","scope2","scope4","sniper","free"};
            for(int i=0;i<keys.length;i++)prefs.edit().putInt(keys[i],vals[i]).apply();
            Toast.makeText(this,"Tutup lalu buka kembali aplikasi untuk memuat preset.",Toast.LENGTH_LONG).show();
        });
        section("DRILL DRAG HEADSHOT","1. Masuk Training Ground.\n2. Arahkan crosshair ke area dada.\n3. Tarik tombol tembak ke atas dengan gerakan pendek dan konsisten.\n4. Ulangi 10–15 menit, lalu evaluasi akurasi.\n5. Ubah sensitivitas sedikit demi sedikit, jangan sekaligus.");
        section("HUD & DPI","HUD: letakkan tombol tembak di posisi yang mudah dijangkau jempol, atur ukuran agar nyaman, lalu uji di Training Ground.\n\nDPI: gunakan pengaturan layar bawaan perangkat terlebih dahulu. Hindari nilai ekstrem karena bisa mengganggu tampilan dan kontrol.");
        section("PERFORMA","• Tutup aplikasi latar belakang.\n• Gunakan pengaturan grafis yang stabil.\n• Sisakan ruang penyimpanan.\n• Hindari aplikasi pihak ketiga yang meminta akses akun atau memodifikasi game.");
        section("CATATAN KEAMANAN","NAD FF AIM TOOL adalah panduan latihan saja. Bukan cheat, aimbot, injector, macro, atau anti-ban. Gunakan fitur resmi game dan patuhi ketentuan Free Fire.");
        root.addView(text("NAD MOD HUB  •  VERSION 1.0",11,MUTED,true));
    }
}
