/*
 * Copyright (C) 2017 takahirom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.takahirom.motion_app.animation.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class BlinkDebugTransition extends Transition {
    private static final String PROP_BOUNDS = "mdap:fabTransform:bounds";

    private static final String[] transitionProperties = {
            PROP_BOUNDS
    };

    public BlinkDebugTransition() {
        super();
    }

    public BlinkDebugTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDuration(5000);
    }

    @Override
    public String[] getTransitionProperties() {
        return transitionProperties;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }


    private void captureValues(TransitionValues transitionValues) {
        final View view = transitionValues.view;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) return;

        transitionValues.values.put(PROP_BOUNDS, new Rect(view.getLeft(), view.getTop(),
                view.getRight(), view.getBottom()));
    }


    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
                                   TransitionValues endValues) {
        if (startValues == null || endValues == null) return null;

        final Rect startBounds = (Rect) startValues.values.get(PROP_BOUNDS);
        final Rect endBounds = (Rect) endValues.values.get(PROP_BOUNDS);

        final View view = endValues.view;
        ValueAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alphaAnim.setDuration(5);
        alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnim.setRepeatMode(ValueAnimator.REVERSE);
        return alphaAnim;
    }

}
