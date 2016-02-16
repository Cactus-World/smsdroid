/*
 * Copyright (C) 2010 Felix Bechstein
 * 
 * This file is part of SMSdroid.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.ub0r.android.smsdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;

/**
 * Listen for new mms.
 *
 * @author flx
 */
public class MmsReceiver extends BroadcastReceiver {
    /**
     * Intent.action for receiving SMS.
     */
    private static final String ACTION_SMS_OLD = "android.provider.Telephony.SMS_RECEIVED";

    private static final String ACTION_SMS_NEW = "android.provider.Telephony.SMS_DELIVER";

    @Override
    public final void onReceive(final Context context, final Intent intent) {
        if (SMSdroid.isDefaultApp(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
                if (intent.getAction().equals(ACTION_SMS_NEW))
                    SmsReceiver.handleOnReceive(this, context, intent, ACTION_SMS_NEW);
                else if (intent.getAction().equals(SenderActivity.MESSAGE_SENT_ACTION))
                    SmsReceiver.handleSent(context, intent, this.getResultCode());
            }
            else
                SmsReceiver.handleOnReceive(this, context, intent, ACTION_SMS_OLD);
        }
    }
}
