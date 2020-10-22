package mz.co.sidy.pvn.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import mz.co.sidy.pvn.R;


/**
 * Created by Sidónio Goenha on 03/31/2017.
 */

public class ErrorAlert {

    /**
     * @param c
     * @param view
     * @param message
     */
    public static void showError(final Context c, View view, String message) {
        final LinearLayout parentView = (LinearLayout) view.getParent();

        if (parentView.getTag(R.string.view_error) != null) {

        } else {
            for (int i = 0; i < parentView.getChildCount(); i++) {
                if (parentView.getChildAt(i) instanceof RadioGroup) {
                    RadioGroup radioGroup = (RadioGroup) parentView.getChildAt(i);
                    for (int j = 0; j < radioGroup.getChildCount(); j++) {
                        radioGroup.getChildAt(j).setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (parentView.getTag(R.string.view_error) != null) {
                                    int numeroDeFilhos = parentView.getChildCount();
                                    parentView.removeViewAt(numeroDeFilhos - 1);
                                    parentView.removeViewAt(numeroDeFilhos - 2);
                                    parentView.setTag(R.string.view_error, null);

                                }
                                return false;
                            }
                        });
                    }
                }

                parentView.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (parentView.getTag(R.string.view_error) != null) {
                            int numeroDeFilhos = parentView.getChildCount();
                            parentView.removeViewAt(numeroDeFilhos - 1);
                            parentView.removeViewAt(numeroDeFilhos - 2);
                            parentView.setTag(R.string.view_error, null);
                        }

                        return false;
                    }
                });
            }

            parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (parentView.getTag(R.string.view_error) != null) {
                        int numeroDeFilhos = parentView.getChildCount();
                        parentView.removeViewAt(numeroDeFilhos - 1);
                        parentView.removeViewAt(numeroDeFilhos - 2);
                        parentView.setTag(R.string.view_error, null);
                    }
                }
            });

            parentView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (parentView.getTag(R.string.view_error) != null) {
                        int numeroDeFilhos = parentView.getChildCount();
                        parentView.removeViewAt(numeroDeFilhos - 1);
                        parentView.removeViewAt(numeroDeFilhos - 2);
                        parentView.setTag(R.string.view_error, null);
                    }
                }
            });

            LinearLayout linearLayout = new LinearLayout(c);
            linearLayout.setBackgroundColor(c.getResources().getColor(R.color.red_error));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
            linearLayout.setLayoutParams(params);

            TextView errorMessage = new TextView(c);
            errorMessage.setTextSize(17);
            errorMessage.setFocusable(true);
            errorMessage.setFocusableInTouchMode(true);
            errorMessage.setTextColor(c.getResources().getColor(R.color.red_error));
            errorMessage.requestFocus();

            errorMessage.setText(message);
            parentView.setTag(R.string.view_error, "error");
            parentView.addView(errorMessage);
            parentView.addView(linearLayout);
        }
    }

    public static void showError1(final Context c, View view, String message) {
        final FrameLayout parentView = (FrameLayout) view.getParent();
        if (parentView.getTag(R.string.view_error) != null) {
        } else {
            for (int i = 0; i < parentView.getChildCount(); i++) {
                if (parentView.getChildAt(i) instanceof RadioGroup) {
                    RadioGroup radioGroup = (RadioGroup) parentView.getChildAt(i);
                    for (int j = 0; j < radioGroup.getChildCount(); j++) {
                        radioGroup.getChildAt(j).setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (parentView.getTag(R.string.view_error) != null) {
                                    int numeroDeFilhos = parentView.getChildCount();
                                    parentView.removeViewAt(numeroDeFilhos - 1);
                                    parentView.removeViewAt(numeroDeFilhos - 2);
                                    parentView.setTag(R.string.view_error, null);

                                }
                                return false;
                            }
                        });
                    }
                }

                parentView.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (parentView.getTag(R.string.view_error) != null) {
                            int numeroDeFilhos = parentView.getChildCount();
                            parentView.removeViewAt(numeroDeFilhos - 1);
                            parentView.removeViewAt(numeroDeFilhos - 2);
                            parentView.setTag(R.string.view_error, null);
                        }

                        return false;
                    }
                });
            }

            parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (parentView.getTag(R.string.view_error) != null) {
                        int numeroDeFilhos = parentView.getChildCount();
                        parentView.removeViewAt(numeroDeFilhos - 1);
                        parentView.removeViewAt(numeroDeFilhos - 2);
                        parentView.setTag(R.string.view_error, null);
                    }
                }
            });

            parentView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (parentView.getTag(R.string.view_error) != null) {
                        int numeroDeFilhos = parentView.getChildCount();
                        parentView.removeViewAt(numeroDeFilhos - 1);
                        parentView.removeViewAt(numeroDeFilhos - 2);
                        parentView.setTag(R.string.view_error, null);
                    }
                }
            });

            FrameLayout frameLayout = new FrameLayout(c);
            frameLayout.setBackgroundColor(ContextCompat.getColor(c, R.color.red_error));

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 2);
            frameLayout.setLayoutParams(params);

            TextView errorMessage = new TextView(c);
            errorMessage.setTextSize(17);
            errorMessage.setFocusable(true);
            errorMessage.setFocusableInTouchMode(true);
            errorMessage.setTextColor(ContextCompat.getColor(c, R.color.red_error));
            errorMessage.requestFocus();

            errorMessage.setText(message);
            parentView.setTag(R.string.view_error, "error");
            parentView.addView(errorMessage);
            parentView.addView(frameLayout);
        }
    }

    /**
     * @param c
     * @param view
     * @param message
     * @param style
     */
    public static void showError(final Context c, View view, String message, String style) {
        if (style.equals(AppConstants.ERROR_STYLE_LINEAR_TEXT)) {
            final LinearLayout parentView = (LinearLayout) view.getParent();

            if (parentView.getTag(R.string.view_error) == null) {
                for (int i = 0; i < parentView.getChildCount(); i++) {
                    if (parentView.getChildAt(i) instanceof RadioGroup) {
                        RadioGroup radioGroup = (RadioGroup) parentView.getChildAt(i);

                        for (int j = 0; j < radioGroup.getChildCount(); j++) {
                            radioGroup.getChildAt(j).setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (parentView.getTag(R.string.view_error) != null) {
                                        int numeroDeFilhos = parentView.getChildCount();
                                        parentView.removeViewAt(numeroDeFilhos - 1);
                                        parentView.removeViewAt(numeroDeFilhos - 2);
                                        parentView.setTag(R.string.view_error, null);
                                    }

                                    return false;
                                }
                            });
                        }
                    }

                    parentView.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (parentView.getTag(R.string.view_error) != null) {
                                int numeroDeFilhos = parentView.getChildCount();
                                parentView.removeViewAt(numeroDeFilhos - 1);
                                parentView.removeViewAt(numeroDeFilhos - 2);
                                parentView.setTag(R.string.view_error, null);
                            }

                            return false;
                        }
                    });
                }

                parentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (parentView.getTag(R.string.view_error) != null) {
                            int numeroDeFilhos = parentView.getChildCount();
                            parentView.removeViewAt(numeroDeFilhos - 1);
                            parentView.removeViewAt(numeroDeFilhos - 2);
                            parentView.setTag(R.string.view_error, null);
                        }
                    }
                });

                parentView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (parentView.getTag(R.string.view_error) != null) {
                            int numeroDeFilhos = parentView.getChildCount();
                            parentView.removeViewAt(numeroDeFilhos - 1);
                            parentView.removeViewAt(numeroDeFilhos - 2);
                            parentView.setTag(R.string.view_error, null);
                        }
                    }
                });

                LinearLayout linearLayout = new LinearLayout(c);
                linearLayout.setBackgroundColor(c.getResources().getColor(R.color.red_error));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
                linearLayout.setLayoutParams(params);

                TextView errorMessage = new TextView(c);
                errorMessage.setTextSize(17);
                errorMessage.setFocusable(true);
                errorMessage.setFocusableInTouchMode(true);
                errorMessage.setTextColor(c.getResources().getColor(R.color.red_error));
                errorMessage.requestFocus();

                errorMessage.setText(message);
                parentView.setTag(R.string.view_error, "error");
                parentView.addView(errorMessage);
                parentView.addView(linearLayout);
            }
        } else if (style.equals(AppConstants.ERROR_STYLE_ICON_TEXT)) {
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else if (view instanceof DatePicker || view instanceof ListView || view instanceof RadioGroup) {
                final LinearLayout parentView = (LinearLayout) view.getParent();

                if (parentView.getTag(R.string.view_error) == null) {
                    for (int i = 0; i < parentView.getChildCount(); i++) {
                        if (parentView.getChildAt(i) instanceof RadioGroup) {
                            RadioGroup radioGroup = (RadioGroup) parentView.getChildAt(i);

                            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                                radioGroup.getChildAt(j).setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        if (parentView.getTag(R.string.view_error) != null) {
                                            int numeroDeFilhos = parentView.getChildCount();
                                            parentView.removeViewAt(numeroDeFilhos - 1);
                                            parentView.removeViewAt(numeroDeFilhos - 2);
                                            parentView.setTag(R.string.view_error, null);
                                        }

                                        return false;
                                    }
                                });
                            }
                        }

                        parentView.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (parentView.getTag(R.string.view_error) != null) {
                                    int numeroDeFilhos = parentView.getChildCount();
                                    parentView.removeViewAt(numeroDeFilhos - 1);
                                    parentView.removeViewAt(numeroDeFilhos - 2);
                                    parentView.setTag(R.string.view_error, null);
                                }

                                return false;
                            }
                        });
                    }

                    parentView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (parentView.getTag(R.string.view_error) != null) {
                                int numeroDeFilhos = parentView.getChildCount();
                                parentView.removeViewAt(numeroDeFilhos - 1);
                                parentView.removeViewAt(numeroDeFilhos - 2);
                                parentView.setTag(R.string.view_error, null);
                            }
                        }
                    });

                    parentView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (parentView.getTag(R.string.view_error) != null) {
                                int numeroDeFilhos = parentView.getChildCount();
                                parentView.removeViewAt(numeroDeFilhos - 1);
                                parentView.removeViewAt(numeroDeFilhos - 2);
                                parentView.setTag(R.string.view_error, null);
                            }
                        }
                    });

                    LinearLayout linearLayout = new LinearLayout(c);
                    linearLayout.setBackgroundColor(c.getResources().getColor(R.color.red_error));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
                    linearLayout.setLayoutParams(params);

                    TextView errorMessage = new TextView(c);
                    errorMessage.setTextSize(17);
                    errorMessage.setFocusable(true);
                    errorMessage.setFocusableInTouchMode(true);
                    errorMessage.setTextColor(c.getResources().getColor(R.color.red_error));
                    errorMessage.requestFocus();

                    errorMessage.setText(message);
                    parentView.setTag(R.string.view_error, "error");
                    parentView.addView(errorMessage);
                    parentView.addView(linearLayout);
                }
            } else if (view instanceof Spinner) {
                LinearLayout parentView = (LinearLayout) view.getParent();
                Spinner spinner = (Spinner) view;
                View selectedView = spinner.getSelectedView();

                if (selectedView != null && selectedView instanceof TextView) {
                    TextView selectedTextView = (TextView) selectedView;
                    selectedTextView.setError(message);
                    selectedTextView.requestFocus();
                }
            }
        }
    }
}
