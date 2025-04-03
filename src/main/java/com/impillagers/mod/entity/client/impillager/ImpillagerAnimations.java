package com.impillagers.mod.entity.client.impillager;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class ImpillagerAnimations {

        public static final Animation IDLE = Animation.Builder.create(8.0F).looping()
                .addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -2.5F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(4.5545F, -0.5139F, -3.6096F), Transformation.Interpolations.CUBIC),
                        new Keyframe(4.0F, AnimationHelper.createRotationalVector(-2.5548F, -0.7577F, -4.9549F), Transformation.Interpolations.CUBIC),
                        new Keyframe(6.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, -2.5F), Transformation.Interpolations.CUBIC),
                        new Keyframe(8.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -2.5F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 2.5F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 2.5F), Transformation.Interpolations.CUBIC),
                        new Keyframe(4.0F, AnimationHelper.createRotationalVector(-2.5548F, 0.7577F, 4.9549F), Transformation.Interpolations.CUBIC),
                        new Keyframe(6.0F, AnimationHelper.createRotationalVector(4.5545F, 0.5139F, 3.6096F), Transformation.Interpolations.CUBIC),
                        new Keyframe(8.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 2.5F), Transformation.Interpolations.CUBIC)
                ))
                .build();

        public static final Animation WALKING = Animation.Builder.create(0.25F).looping()
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(17.4814F, 0.4336F, -2.4621F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(17.4907F, -0.434F, 2.4621F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("body", new Transformation(Transformation.Targets.TRANSLATE,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_leg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.0417F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.0833F, AnimationHelper.createRotationalVector(-2.5017F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.125F, AnimationHelper.createRotationalVector(-10.0017F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.1667F, AnimationHelper.createRotationalVector(10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.2083F, AnimationHelper.createRotationalVector(20.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_leg", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-10.0017F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.0417F, AnimationHelper.createRotationalVector(-2.5017F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.0833F, AnimationHelper.createRotationalVector(12.4983F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.125F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.1667F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.2083F, AnimationHelper.createRotationalVector(-2.5017F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.0017F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();
}