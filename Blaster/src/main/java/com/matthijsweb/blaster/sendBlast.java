package com.matthijsweb.blaster;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import com.matthijsweb.blaster.database.RemoteFunctions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sendBlast {
    Object irdaService;
    Method irWrite;
    SparseArray<String> irData;
    Context context;
    // For phones without a IR blaster, just pretend you're sending something
    Boolean dummy;

    public sendBlast(Context ctx) {
        context = ctx;
    }

    //Set the buttons for the remote controller
    public void setButtons() {
        irData = new SparseArray<String>();

        RemoteFunctions remote = new RemoteFunctions();

        irData.put(
                R.id.buttonPower,
                hex2dec(remote.getRemoteCode(1)));
        irData.put(
                R.id.buttonVolDown,
                hex2dec(remote.getRemoteCode(12)));
        irData.put(
                R.id.buttonMute,
                hex2dec(remote.getRemoteCode(2)));
        irData.put(
                R.id.buttonVolUp,
                hex2dec(remote.getRemoteCode(15)));
        irData.put(
                R.id.buttonTuner,
                hex2dec(remote.getRemoteCode(3)));
        irData.put(
                R.id.buttonPhono,
                hex2dec(remote.getRemoteCode(4)));
        irData.put(
                R.id.buttonCD,
                hex2dec(remote.getRemoteCode(5)));
        irData.put(
                R.id.buttonAux,
                hex2dec(remote.getRemoteCode(6)));
    }

    /**
     * Initialize the ir blaster, and check if this phone is capable of sending IR blasts
     */
    public void irInit() {
        try {
            irdaService = context.getSystemService("irda");
            dummy = false;
        } catch (Exception e) {
            dummy = true;
        }

        try {

            Class c = irdaService.getClass();
            Class p[] = { String.class };
            try {
                irWrite = c.getMethod("write_irsend", p);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            dummy = true;
        }
    }

    /**
     * Send the blast to the blaster with a String input
     * @param data
     */
    public void irSend(String data) {
        if (data != null && !dummy) {
            try {
                irWrite.invoke(irdaService, data);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send the blast to the blaster with a view input
     * @param view
     */
    public void irSend(View view) {
        if (!dummy) {
            String data = irData.get(view.getId());
            if (data != null) {
                try {
                    irWrite.invoke(irdaService, data);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Change the hex value to a decimal value
     * @param irData
     * @return
     */
    protected String hex2dec(String irData) {
        List<String> list = new ArrayList<String>(Arrays.asList(irData
                .split(" ")));
        list.remove(0); // dummy
        int frequency = Integer.parseInt(list.remove(0), 16); // frequency
        list.remove(0); // seq1
        list.remove(0); // seq2

        for (int i = 0; i < list.size(); i++) {
            list.set(i, Integer.toString(Integer.parseInt(list.get(i), 16)));
        }

        frequency = (int) (1000000 / (frequency * 0.241246));
        list.add(0, Integer.toString(frequency));

        irData = "";
        for (String s : list) {
            irData += s + ",";
        }
        return irData;
    }

}
