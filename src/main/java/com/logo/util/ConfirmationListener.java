package com.logo.util;

public interface ConfirmationListener {

	/**
     * Event when confirmation result is confirmed.
     */
    public void onConfirm();

	/**
	 * Event when confirmation result is canceled.
	 */
	public void onCancel();

}
