package se.miun.dt170.antonsskafferi.data.utility;

import android.os.CountDownTimer;

import se.miun.dt170.antonsskafferi.ui.kitchen.KitchenBongContainerView;

public class KitchenTimer extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */

    private long countDownInterval;
    private long millisInFuture;

    public KitchenTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        new KitchenTimer(millisInFuture, countDownInterval);
    }
}
