package net.darkblade.smopmod.entity.client.niras.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class NirasmosaurusWaterAnimations {

    public static final AnimationDefinition widle = createWidle();

    private static AnimationDefinition createWidle() {
        return AnimationDefinition.Builder.withLength(4.0F).looping()
                .addAnimation("gLowerjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                ))
                .addAnimation("GEyes", new AnimationChannel(AnimationChannel.Targets.SCALE,
                        new Keyframe(1.25F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.75F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
                ))
                .addAnimation("gHead", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(8.59F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(2.5F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(4.0F, KeyframeAnimations.degreeVec(8.59F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                ))
                .addAnimation("gNeck", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(4.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                ))
                .addAnimation("gNeck", new AnimationChannel(AnimationChannel.Targets.POSITION,
                        new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                ))
                .addAnimation("gBodyparts", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM)
                ))
                .addAnimation("gTail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0954F, -25.7154F, -22.31F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(1.0F, KeyframeAnimations.degreeVec(27.6F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(2.0F, KeyframeAnimations.degreeVec(10.0954F, 25.7154F, 22.31F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(3.0F, KeyframeAnimations.degreeVec(-22.09F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(4.0F, KeyframeAnimations.degreeVec(10.0954F, -25.7154F, -22.31F), AnimationChannel.Interpolations.CATMULLROM)
                ))
                .addAnimation("gFin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -29.75F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, -42.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                        new Keyframe(2.3F, KeyframeAnimations.degreeVec(0.0F, 42.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                        new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, -29.75F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
                ))
                .build();
    }


    public static final AnimationDefinition wsprint =AnimationDefinition.Builder.withLength(2.0F).

    looping()
		.

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.9574F, 0.6518F,-7.4718F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(4.9574F, -0.6518F,7.4718F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(4.9574F, 0.6518F,-7.4718F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 2.9886F,-0.2615F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.posVec(0.0F, 1.9829F,0.2611F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.posVec(0.0F, 2.9886F,-0.2615F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.posVec(0.0F, 1.9829F,0.2611F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.posVec(0.0F, 2.9886F,-0.2615F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.25F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLowerjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(32.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(32.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,-27.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,27.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-27.5F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.8192F, -24.8159F,-8.2364F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(2.8192F, 24.8159F,8.2364F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(2.8192F, -24.8159F,-8.2364F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 20.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, -20.0F,0.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(32.2549F, 5.059F,-30.544F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(43.4336F, 39.355F,-2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(32.2549F, 5.059F,-30.544F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(43.4336F, 39.355F,-2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(32.2549F, 5.059F,-30.544F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(48.2437F, 4.0287F,-30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(43.4336F, 39.355F,-2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(48.2437F, 4.0287F,-30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(43.4336F, 39.355F,-2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(48.2437F, 4.0287F,-30.2586F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(32.2549F, -5.059F,30.544F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(43.4336F, -39.355F,2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(32.2549F, -5.059F,30.544F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(43.4336F, -39.355F,2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(32.2549F, -5.059F,30.544F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(48.2437F, -4.0287F,30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(43.4336F, -39.355F,2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(48.2437F, -4.0287F,30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(43.4336F, -39.355F,2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(48.2437F, -4.0287F,30.2586F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFlippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -2.5F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(0.0F, 2.5F,10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, -2.5F,-10.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTongue",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(-30.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(-30.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeft_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gRight_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition wsleep_preparing =AnimationDefinition.Builder.withLength(4.0F)
            .

    addAnimation("gNirasmo",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(-90.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.degreeVec(-90.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNirasmo",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.posVec(0.0F, -19.0F,-15.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.posVec(0.0F, -19.0F,-15.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(-17.13F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(-20.0F, -0.1848F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(0.0F, 2.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.6F,KeyframeAnimations.degreeVec(0.0F, 2.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gUpperjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, -12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, -12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gFlippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFrontflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(77.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.degreeVec(77.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(-27.3488F, 6.26F,-3.2812F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.6F,KeyframeAnimations.degreeVec(-27.3488F, 6.26F,-3.2812F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(-27.3488F, -6.26F,3.2812F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.6F,KeyframeAnimations.degreeVec(-27.3488F, -6.26F,3.2812F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBackflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(82.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.degreeVec(82.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.9F,KeyframeAnimations.degreeVec(-15.0185F, -3.3761F,6.9969F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.8F,KeyframeAnimations.degreeVec(-15.0185F, -3.3761F,6.9969F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.9F,KeyframeAnimations.degreeVec(-15.0185F, 3.3761F,-6.9969F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.8F,KeyframeAnimations.degreeVec(-15.0185F, 3.3761F,-6.9969F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLowerjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRight_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeft_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.8F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.65F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.55F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.95F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.6F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.1F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.9F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.85F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.05F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.45F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.85F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.2F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.6F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition wsleep =AnimationDefinition.Builder.withLength(2.0F).

    looping()
		.

    addAnimation("gNirasmo",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-90.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-87.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-90.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-92.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-90.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNirasmo",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -19.0F,-15.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 2.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, -2.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 2.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, -12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gFrontflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(77.5F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.3488F, 6.26F,-3.2812F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.3488F, -6.26F,3.2812F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gBackflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(82.5F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-15.0185F, -3.3761F,6.9969F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-15.0185F, 3.3761F,-6.9969F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gRight_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeft_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-23.28F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-23.28F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.9375F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.scaleVec(1.0F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 0.9375F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-23.75F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-23.75F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.9375F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.scaleVec(1.0F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 0.9375F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition wawakening =AnimationDefinition.Builder.withLength(3.0F)
            .

    addAnimation("gNirasmo",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(-90.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(-67.5F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNirasmo",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -19.0F,-15.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 2.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(32.5F, 2.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.25F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.posVec(0.0F, -0.35F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 0.25F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRight_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeft_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFrontflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(77.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(77.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.3488F, 6.26F,-3.2812F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-27.3488F, 6.26F,-3.2812F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.3488F, -6.26F,3.2812F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-27.3488F, -6.26F,3.2812F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBackflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(82.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(82.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-15.0185F, -3.3761F,6.9969F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-15.0185F, -3.3761F,6.9969F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-15.0185F, 3.3761F,-6.9969F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-15.0185F, 3.3761F,-6.9969F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.15F, KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.25F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.2F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.15F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.2F, KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition wbite =AnimationDefinition.Builder.withLength(1.2F)
            .

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-7.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,7.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTroath",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-17.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,17.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-7.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,7.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(27.1423F, 4.599F,-8.8893F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,-20.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,22.5F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gUpperjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.1F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLowerjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.1F,KeyframeAnimations.degreeVec(32.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(32.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFlippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, -27.5F,-30.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(52.5066F, 7.464F,13.3487F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-17.7531F, -9.5327F,3.0351F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 27.5F,30.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(52.5066F, -7.464F,-13.3487F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-17.7531F, 9.5327F,-3.0351F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-17.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(60.0F, 0.0F,-17.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,17.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(60.0F, 0.0F,17.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeft_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gRight_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(40.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.05F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(40.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(40.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(40.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(40.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest_n_corals",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-7.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,7.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition death_roll = AnimationDefinition.Builder.withLength(3.5F)
            .addAnimation("gNirasmo", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -382.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gChest", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gNeck", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(0.6076F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.2F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gTroath", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gHead", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.6F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1F, KeyframeAnimations.degreeVec(-4.7832F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(-20.2836F, 9.3913F, -3.4512F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(5.0503F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.3F, KeyframeAnimations.degreeVec(21.1728F, -18.7472F, -7.096F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gUpperjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("GEyes", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.15F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.15F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.05F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.3F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLowerjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gTail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.65F, KeyframeAnimations.degreeVec(0.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.05F, KeyframeAnimations.degreeVec(-1.6534F, -2.8609F, 30.0413F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(23.0866F, -27.0681F, -43.129F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(0.9008F, -1.2334F, -34.2694F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(16.6248F, 22.2249F, 38.2876F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gFin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 30.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, -30.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 30.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.45F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.posVec(0.8252F, -4.2801F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(17.5F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftbackflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7F, KeyframeAnimations.posVec(1.9526F, -0.4329F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.95F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.posVec(0.8252F, -4.2801F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 42.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightbackflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1F, KeyframeAnimations.posVec(-1.8187F, -0.832F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.posVec(-1.8187F, -0.832F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.posVec(-0.8252F, -4.2801F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeft_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gRight_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gCoral1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral3", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral4", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral5", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gChest_n_corals", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 32.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -27.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 57.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 35.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.35F, KeyframeAnimations.posVec(-0.8252F, -4.2801F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition weating =AnimationDefinition.Builder.withLength(4.0F)
            .

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,-15.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,15.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-15.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.95F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.scaleVec(1.0F, 0.95F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.95F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.scaleVec(1.0F, 0.95F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-12.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,12.5F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.scaleVec(1.0F, 0.9F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.25F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 2.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.posVec(0.0F, -2.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.posVec(0.0F, 2.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTroath",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.scaleVec(1.05F, 1.05F,1.05F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.scaleVec(1.05F, 1.05F,1.05F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.85F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.05F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.25F,KeyframeAnimations.scaleVec(1.05F, 1.05F,1.05F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(8.59F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.25F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.8F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(8.59F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.05F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.posVec(0.0F, 0.0F,-2.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.posVec(0.0F, 0.0F,-2.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.posVec(0.0F, 0.0F,-2.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.2F,KeyframeAnimations.posVec(0.0F, 0.0F,-2.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.25F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gUpperjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(1.25F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLowerjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRight_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(0.65F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeft_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(0.65F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0954F, -25.7154F,-22.31F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(27.6F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(10.0954F, 25.7154F,22.31F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(-22.09F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(10.0954F, -25.7154F,-22.31F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -29.75F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, -42.5F,0.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(0.0F, 42.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, -29.75F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFlippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,-15.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,15.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-15.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-25.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.posVec(0.0F, -4.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,25.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,25.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -4.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.posVec(0.0F, -4.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-25.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.posVec(0.0F, -4.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,25.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.LINEAR),
            new

    Keyframe(4.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,25.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -4.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(4.0F,KeyframeAnimations.posVec(0.0F, -4.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition wwarning1 =AnimationDefinition.Builder.withLength(2.0F)
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-22.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(0.0F, -10.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTroath",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.posVec(0.0F, -0.2497F,0.013F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.posVec(0.0F, -0.2497F,0.013F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLowerjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(0.0F, -10.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.degreeVec(0.0F, -22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.45F,KeyframeAnimations.degreeVec(0.0F, 22.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFrontflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(30.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(42.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(0.0F, 52.5F,-32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(31.038F, 44.7257F,8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(23.0936F, 11.2146F,-4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(0.0F, -52.5F,32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(31.038F, -44.7257F,-8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(23.0936F, -11.2146F,4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBackflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(30.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(42.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 52.5F,-32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(31.038F, 44.7257F,8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(23.0936F, 11.2146F,-4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, -52.5F,32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(31.038F, -44.7257F,-8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(23.0936F, -11.2146F,4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(-6.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(-6.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(-6.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(-6.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(-11.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,-10.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(-6.7678F, 4.2453F,9.5623F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest_n_corals",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition wwarning2 =AnimationDefinition.Builder.withLength(2.0F)
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-3.03F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(17.6848F, -3.2055F,-24.3186F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(6.88F, 16.1299F,23.476F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(0.0F, -25.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(0.0F, 25.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, -12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.1F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.scaleVec(1.0F, 0.95F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTroath",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.65F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.posVec(0.0F, -0.2497F,0.013F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.posVec(0.0F, -0.2497F,0.013F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLowerjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(17.6848F, -3.2055F,-24.3186F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(6.88F, 16.1299F,23.476F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, -25.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 25.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, -12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(0.0F, 12.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFrontflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(30.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(42.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(0.0F, -52.5F,32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(31.038F, -44.7257F,-8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(23.0936F, -11.2146F,4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBackflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(30.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(42.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, -52.5F,32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(31.038F, -44.7257F,-8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(23.0936F, -11.2146F,4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(-14.8774F, -1.936F,-7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.degreeVec(-14.8774F, 1.936F,7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(-14.8774F, -1.936F,-7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.degreeVec(-14.8774F, 1.936F,7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(-14.8774F, -1.936F,-7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.degreeVec(-14.8774F, 1.936F,7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(-14.8774F, -1.936F,-7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.degreeVec(-14.8774F, 1.936F,7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-15.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(-14.8774F, -1.936F,-7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.degreeVec(-14.8774F, 1.936F,7.2472F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest_n_corals",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest_n_corals",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.1F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.posVec(0.0F, -0.875F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(0.0F, 52.5F,-32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(31.038F, 44.7257F,8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(23.0936F, 11.2146F,-4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 52.5F,-32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(31.038F, 44.7257F,8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(23.0936F, 11.2146F,-4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition wwarning3 =AnimationDefinition.Builder.withLength(2.0F)
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.1F,KeyframeAnimations.degreeVec(-35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-21.3496F, -2.744F,-6.9827F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-15.2315F, 1.9809F,7.2351F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-8.098F, -1.0625F,-7.4248F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(0.1205F, -0.0159F,7.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(40.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(40.6782F, -9.5438F,-8.1102F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(35.6782F, 9.5438F,8.1102F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(30.6782F, -9.5438F,-8.1102F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(25.6782F, 9.5438F,8.1102F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(18.1782F, -9.5438F,-8.1102F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.1563F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.posVec(0.0F, 2.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.posVec(0.0F, -0.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.05F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.scaleVec(1.0F, 0.95F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTroath",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.45F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.posVec(0.0F, -0.25F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.posVec(0.0F, -0.25F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLowerjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(40.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, -15.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 15.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, -15.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(0.0F, 15.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, -15.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFrontflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(30.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(42.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(0.0F, -52.5F,32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(31.038F, -44.7257F,-8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(23.0936F, -11.2146F,4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBackflippers",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(30.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(42.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, -52.5F,32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(31.038F, -44.7257F,-8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(23.0936F, -11.2146F,4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(-14.7822F, -2.5759F,-9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-14.5108F, 3.841F,14.5108F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-14.9455F, -1.2926F,-4.8304F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-14.7822F, 2.5759F,9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-14.5211F, 1.2476F,4.8025F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(-14.7822F, -2.5759F,-9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-14.5108F, 3.841F,14.5108F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-14.9455F, -1.2926F,-4.8304F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-14.7822F, 2.5759F,9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-14.5211F, 1.2476F,4.8025F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(-14.7822F, -2.5759F,-9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-14.5108F, 3.841F,14.5108F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-14.9455F, -1.2926F,-4.8304F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-14.7822F, 2.5759F,9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-14.5211F, 1.2476F,4.8025F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(-14.7822F, -2.5759F,-9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-14.5108F, 3.841F,14.5108F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-14.9455F, -1.2926F,-4.8304F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-14.7822F, 2.5759F,9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-14.5211F, 1.2476F,4.8025F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-17.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(-14.7822F, -2.5759F,-9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-14.5108F, 3.841F,14.5108F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-14.9455F, -1.2926F,-4.8304F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-14.7822F, 2.5759F,9.6658F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-14.5211F, 1.2476F,4.8025F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest_n_corals",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.1563F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest_n_corals",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.posVec(0.0F, 2.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.posVec(0.0F, -0.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest_n_corals",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.05F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.scaleVec(1.0F, 0.95F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(0.0F, 52.5F,-32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(31.038F, 44.7257F,8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(23.0936F, 11.2146F,-4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 52.5F,-32.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(31.038F, 44.7257F,8.0346F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(23.0936F, 11.2146F,-4.9223F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition wroar =AnimationDefinition.Builder.withLength(3.5F)
            .

    addAnimation("gNeck",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-32.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(-12.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-12.6866F, 9.7606F,-2.1856F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.25F,KeyframeAnimations.degreeVec(-12.6866F, -9.7606F,2.1856F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(-12.6866F, 9.7606F,-2.1856F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.25F,KeyframeAnimations.degreeVec(-12.6866F, -9.7606F,2.1856F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(-12.6866F, 9.7606F,-2.1856F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gHead",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.25F,KeyframeAnimations.degreeVec(-40.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-0.9015F, 23.004F,-14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-0.9015F, -23.004F,-14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(-0.9015F, -23.004F,14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-0.9015F, 23.004F,14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(-0.9015F, 23.004F,-14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(-0.9015F, -23.004F,-14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(-0.9015F, -23.004F,14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.6F,KeyframeAnimations.degreeVec(-0.9015F, 23.004F,14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.8F,KeyframeAnimations.degreeVec(-0.9015F, 23.004F,-14.1883F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTail",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-10.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.55F,KeyframeAnimations.degreeVec(12.9264F, 14.6364F,3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(12.9264F, 14.6364F,3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(12.9264F, 14.6364F,3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.45F,KeyframeAnimations.degreeVec(12.9264F, 14.6364F,3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(12.9264F, 14.6364F,3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.05F,KeyframeAnimations.degreeVec(12.9264F, 14.6364F,3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.2F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(12.9264F, 14.6364F,3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.65F,KeyframeAnimations.degreeVec(12.9264F, 14.6364F,3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.8F,KeyframeAnimations.degreeVec(12.9264F, -14.6364F,-3.3191F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.95F,KeyframeAnimations.degreeVec(8.2521F, 11.5281F,15.7521F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(32.2549F, 5.059F,-30.544F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(43.4336F, 39.355F,-2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(32.2549F, 5.059F,-30.544F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(43.4336F, 39.355F,-2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightfrontflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(32.2549F, -5.059F,30.544F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(43.4336F, -39.355F,2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(32.2549F, -5.059F,30.544F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(43.4336F, -39.355F,2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeftbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(48.2437F, 4.0287F,-30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(43.4336F, 39.355F,-2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(48.2437F, 4.0287F,-30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(43.4336F, 39.355F,-2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.degreeVec(-17.2565F, 44.5037F,-130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.9F,KeyframeAnimations.degreeVec(48.2437F, 4.0287F,-30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gRightbackflipper",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(48.2437F, -4.0287F,30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(43.4336F, -39.355F,2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(48.2437F, -4.0287F,30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(43.4336F, -39.355F,2.75F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.degreeVec(-17.2565F, -44.5037F,130.1914F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.9F,KeyframeAnimations.degreeVec(48.2437F, -4.0287F,30.2586F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(-7.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.85F,KeyframeAnimations.degreeVec(5.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gBodyparts",new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.35F,KeyframeAnimations.posVec(0.0F, 2.9886F,-0.2615F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.posVec(0.0F, 1.9829F,0.2611F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.posVec(0.0F, 2.9886F,-0.2615F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.posVec(0.0F, 1.9829F,0.2611F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.85F,KeyframeAnimations.posVec(0.0F, 2.9886F,-0.2615F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.4F,KeyframeAnimations.posVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTroath",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.scaleVec(1.1F, 1.05F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.2F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.6F,KeyframeAnimations.scaleVec(1.15F, 1.1F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.1F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gUpperjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(-2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.2F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.6F,KeyframeAnimations.degreeVec(-20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("GEyes",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.65F,KeyframeAnimations.scaleVec(1.0F, 0.5F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.95F,KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.25F,KeyframeAnimations.scaleVec(1.0F, 1.0F,1.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLowerjaw",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(2.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.8F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.0F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.6F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.0F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.2F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.6F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(35.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.85F,KeyframeAnimations.degreeVec(0.0F, 0.0F,37.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-37.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,37.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-37.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.95F,KeyframeAnimations.degreeVec(0.0F, 0.0F,37.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gTongue",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(-35.1767F, 29.784F,-19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-35.1767F, -29.784F,19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(-35.1767F, 29.784F,-19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-35.1767F, -29.784F,19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(-35.1767F, 29.784F,-19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(-35.1767F, -29.784F,19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.25F,KeyframeAnimations.degreeVec(-35.1767F, 29.784F,-19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(-35.1767F, -29.784F,19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.85F,KeyframeAnimations.degreeVec(-35.1767F, 29.784F,-19.2953F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gFin",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.15F,KeyframeAnimations.degreeVec(27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.45F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.05F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.2F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.65F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.8F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.25F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.4F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.85F,KeyframeAnimations.degreeVec(0.0F, -17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.0F,KeyframeAnimations.degreeVec(0.0F, 17.5F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.25F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gLeft_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gRight_saliva",new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F,1.0F),AnimationChannel.Interpolations.LINEAR)
            ))
            .

    addAnimation("gCoral1",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.15F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral2",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.15F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral3",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.15F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral4",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.15F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gCoral5",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.2F,KeyframeAnimations.degreeVec(-25.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.4F,KeyframeAnimations.degreeVec(20.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.6F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.75F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.95F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.15F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.35F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.55F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.75F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.95F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.15F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.35F,KeyframeAnimations.degreeVec(-27.2989F, 3.4553F,6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.55F,KeyframeAnimations.degreeVec(-27.2989F, -3.4553F,-6.6607F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.75F,KeyframeAnimations.degreeVec(-27.5F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.45F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    addAnimation("gChest_n_corals",new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(0.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(1.9F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.1F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.3F,KeyframeAnimations.degreeVec(0.0F, 0.0F,5.0F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.5F,KeyframeAnimations.degreeVec(0.0F, 0.0F,-2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(2.7F,KeyframeAnimations.degreeVec(0.0F, 0.0F,2.5F),AnimationChannel.Interpolations.CATMULLROM),
            new

    Keyframe(3.15F,KeyframeAnimations.degreeVec(0.0F, 0.0F,0.0F),AnimationChannel.Interpolations.CATMULLROM)
            ))
            .

    build();

    public static final AnimationDefinition waiting = AnimationDefinition.Builder.withLength(4.0F).looping()
            .addAnimation("gNeck", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gHead", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gUpperjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("GEyes", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.25F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("GEyes", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(1.05F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gTongue", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-23.5484F, 19.0377F, -6.246F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.2F, KeyframeAnimations.degreeVec(-19.5142F, -17.0467F, -13.2881F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(-23.5484F, -19.0377F, 6.246F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.2F, KeyframeAnimations.degreeVec(-19.5142F, 17.0467F, 13.2881F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(-23.5484F, 19.0377F, -6.246F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gTail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.8192F, -24.8159F, -8.2364F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(2.8192F, 24.8159F, 8.2364F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.8192F, -24.8159F, -8.2364F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gFin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, -90.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 90.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gLeftbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, -90.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gRightbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 90.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gLeft_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gRight_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gCoral1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral1", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral2", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.95F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 0.95F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral3", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral3", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral4", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral4", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.95F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 0.95F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral5", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral5", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.scaleVec(1.0F, 0.9F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gNirasmo", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.25F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gFlippers", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -6.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition wtamed = AnimationDefinition.Builder.withLength(2.2F)
            .addAnimation("gNeck", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(-38.4635F, -11.8491F, 9.2643F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.85F, KeyframeAnimations.degreeVec(-38.4635F, 11.8491F, -9.2643F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.1F, KeyframeAnimations.degreeVec(-38.4635F, -11.8491F, 9.2643F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.35F, KeyframeAnimations.degreeVec(-38.4635F, 11.8491F, -9.2643F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gHead", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.25F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.65F, KeyframeAnimations.degreeVec(-5.1208F, -12.4517F, 1.1069F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(-5.1208F, 12.4517F, -1.1069F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.15F, KeyframeAnimations.degreeVec(-5.1208F, -12.4517F, 1.1069F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.degreeVec(-5.1208F, 12.4517F, -1.1069F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.75F, KeyframeAnimations.degreeVec(12.3792F, 12.4517F, -1.1069F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gTail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(56.265F, 9.9319F, 14.4817F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.85F, KeyframeAnimations.degreeVec(56.265F, -9.9319F, -14.4817F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.1F, KeyframeAnimations.degreeVec(56.265F, 9.9319F, 14.4817F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.35F, KeyframeAnimations.degreeVec(56.265F, -9.9319F, -14.4817F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6F, KeyframeAnimations.degreeVec(56.265F, 9.9319F, 14.4817F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.15F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.35F, KeyframeAnimations.degreeVec(1.8547F, 32.7591F, -109.712F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.55F, KeyframeAnimations.degreeVec(3.3441F, -62.187F, -113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.75F, KeyframeAnimations.degreeVec(25.8441F, -62.187F, -113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.95F, KeyframeAnimations.degreeVec(3.3441F, -62.187F, -113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.15F, KeyframeAnimations.degreeVec(25.8441F, -62.187F, -113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.35F, KeyframeAnimations.degreeVec(3.3441F, -62.187F, -113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.85F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.15F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.65F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.85F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 90.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(1.8547F, -32.7591F, 109.712F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(3.3441F, 62.187F, 113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.8F, KeyframeAnimations.degreeVec(25.8441F, 62.187F, 113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0F, KeyframeAnimations.degreeVec(3.3441F, 62.187F, 113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.degreeVec(25.8441F, 62.187F, 113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.degreeVec(3.3441F, 62.187F, 113.6743F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.05F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.85F, KeyframeAnimations.degreeVec(0.0F, -45.0F, -15.1075F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.45F, KeyframeAnimations.degreeVec(-22.5F, -40.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.85F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 15.1075F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.45F, KeyframeAnimations.degreeVec(-22.5F, 40.0F, 17.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gBodyparts", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.15F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.45F, KeyframeAnimations.degreeVec(-24.812F, 3.1622F, 6.8042F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(-24.9791F, -1.0563F, -2.266F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.5F, KeyframeAnimations.degreeVec(7.3873F, 1.2988F, -9.9162F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gBodyparts", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.15F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.45F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("GEyes", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.15F, KeyframeAnimations.posVec(0.1024F, 0.3084F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.posVec(0.1024F, 0.3084F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.95F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("GEyes", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.15F, KeyframeAnimations.scaleVec(1.0F, 0.4F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.7F, KeyframeAnimations.scaleVec(1.0F, 0.4F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.95F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.2F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLowerjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.7F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.8F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.1F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.5F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gChin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 32.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 32.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -32.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.85F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 17.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gFin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.25F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.65F, KeyframeAnimations.degreeVec(0.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(0.0F, -27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.15F, KeyframeAnimations.degreeVec(0.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.degreeVec(0.0F, -27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, 27.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.85F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gFrontflippers", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.05F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 11.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.35F, KeyframeAnimations.posVec(0.0F, 11.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.55F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gBackflippers", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.85F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gBackflippers", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.85F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.45F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.45F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral3", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.45F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral4", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.45F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral5", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.45F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition boost = AnimationDefinition.Builder.withLength(2.3F)
            .addAnimation("gNirasmo", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.05F, KeyframeAnimations.posVec(0.0F, -1.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -1.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gBodyparts", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.05F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gBodyparts", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.05F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gNeck", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gHead", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.05F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("GEyes", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.65F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9F, KeyframeAnimations.scaleVec(1.0F, 0.2F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.scaleVec(1.0F, 0.2F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLowerjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.65F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gTail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.4F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gFin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(43.4336F, 39.355F, -2.75F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7F, KeyframeAnimations.degreeVec(-27.1211F, 48.6367F, -144.4092F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.15F, KeyframeAnimations.degreeVec(79.5548F, 11.322F, 12.2768F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.4F, KeyframeAnimations.degreeVec(32.2549F, 5.059F, -30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(12.2549F, 5.059F, -30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(43.4336F, -39.355F, 2.75F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.7F, KeyframeAnimations.degreeVec(-27.1211F, -48.6367F, 144.4092F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.15F, KeyframeAnimations.degreeVec(79.5548F, -11.322F, -12.2768F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.4F, KeyframeAnimations.degreeVec(32.2549F, -5.059F, 30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(12.2549F, -5.059F, 30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(43.4336F, 39.355F, -2.75F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-17.2565F, 44.5037F, -130.1914F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.2F, KeyframeAnimations.degreeVec(84.9978F, -1.3095F, 11.4474F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(32.2549F, 5.059F, -30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7F, KeyframeAnimations.degreeVec(17.2549F, 5.059F, -30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(43.4336F, -39.355F, 2.75F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(-17.2565F, -44.5037F, 130.1914F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.2F, KeyframeAnimations.degreeVec(84.9978F, 1.3095F, -11.4474F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(32.2549F, -5.059F, 30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.7F, KeyframeAnimations.degreeVec(17.2549F, -5.059F, 30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeft_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gRight_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gCoral1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.15F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.85F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral3", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.55F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.9F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.2F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral4", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.15F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.85F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral5", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.6F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.95F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition swam_dive = AnimationDefinition.Builder.withLength(3.0F)
            .addAnimation("gNirasmo", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1F, KeyframeAnimations.degreeVec(-75.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.75F, KeyframeAnimations.degreeVec(57.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.25F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gNirasmo", new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.6F, KeyframeAnimations.posVec(0.0F, -4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.posVec(0.0F, 62.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -36.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.85F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral3", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral4", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral5", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.35F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.65F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.95F, KeyframeAnimations.degreeVec(-37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gNeck", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.4F, KeyframeAnimations.degreeVec(27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.0F, KeyframeAnimations.degreeVec(-27.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.9F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gHead", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.55F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gUpperjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.15F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(-30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLowerjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.15F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRight_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gLeft_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gTail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.45F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.45F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.95F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gFin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.5F, KeyframeAnimations.degreeVec(37.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.85F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.5F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.15F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(-27.1211F, 48.6367F, -144.4092F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(79.5548F, 11.322F, 12.2768F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(32.2549F, 5.059F, -30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(-15.7314F, 15.7937F, -103.3819F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.degreeVec(-27.1211F, 48.6367F, -144.4092F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.95F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3F, KeyframeAnimations.degreeVec(-27.1211F, -48.6367F, 144.4092F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.75F, KeyframeAnimations.degreeVec(79.5548F, -11.322F, -12.2768F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.0F, KeyframeAnimations.degreeVec(32.2549F, -5.059F, 30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.25F, KeyframeAnimations.degreeVec(-15.7314F, -15.7937F, 103.3819F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.1F, KeyframeAnimations.degreeVec(-27.1211F, -48.6367F, 144.4092F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.95F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(-17.2565F, 44.5037F, -130.1914F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8F, KeyframeAnimations.degreeVec(84.9978F, -1.3095F, 11.4474F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.05F, KeyframeAnimations.degreeVec(32.2549F, 5.059F, -30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.3F, KeyframeAnimations.degreeVec(24.0316F, -9.5718F, -91.9341F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.15F, KeyframeAnimations.degreeVec(-17.2565F, 44.5037F, -130.1914F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.35F, KeyframeAnimations.degreeVec(-17.2565F, -44.5037F, 130.1914F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.8F, KeyframeAnimations.degreeVec(84.9978F, 1.3095F, -11.4474F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.05F, KeyframeAnimations.degreeVec(32.2549F, -5.059F, 30.544F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(1.3F, KeyframeAnimations.degreeVec(24.0316F, 9.5718F, 91.9341F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.15F, KeyframeAnimations.degreeVec(-17.2565F, -44.5037F, 130.1914F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(2.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition wdeath = AnimationDefinition.Builder.withLength(2.5F)
            .addAnimation("gNirasmo", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.15F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 110.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 102.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 110.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 110.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(-14.4328F, 13.9954F, -46.7808F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral1", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.15F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.4F, KeyframeAnimations.scaleVec(1.0F, 0.75F, 0.75F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.65F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.9F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.25F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral2", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(-14.4328F, 13.9954F, -46.7808F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral2", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.15F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.4F, KeyframeAnimations.scaleVec(1.0F, 0.75F, 0.75F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.65F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.9F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.25F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral3", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(-14.4328F, 13.9954F, -46.7808F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral3", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.15F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.4F, KeyframeAnimations.scaleVec(1.0F, 0.75F, 0.75F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.65F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.9F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.25F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral4", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(-14.4328F, 13.9954F, -46.7808F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral4", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.15F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.4F, KeyframeAnimations.scaleVec(1.0F, 0.75F, 0.75F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.65F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.9F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.25F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral5", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.7F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(-14.4328F, 13.9954F, -46.7808F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gCoral5", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.15F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.4F, KeyframeAnimations.scaleVec(1.0F, 0.75F, 0.75F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.65F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.9F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.25F, KeyframeAnimations.scaleVec(1.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gNeck", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.05F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.35F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.85F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.5F, KeyframeAnimations.degreeVec(-16.4487F, 3.6882F, -21.9765F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(-16.4487F, 3.6882F, -21.9765F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gHead", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.1F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.9F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gUpperjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.8F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLowerjaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.8F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRight_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gLeft_saliva", new AnimationChannel(AnimationChannel.Targets.SCALE,
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -57.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -57.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.65F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftfrontflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 57.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 57.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.65F, KeyframeAnimations.degreeVec(26.7034F, 26.411F, -32.5229F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(26.7034F, 26.411F, -32.5229F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightfrontflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.8F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.65F, KeyframeAnimations.posVec(1.0822F, -4.8815F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.posVec(1.0822F, -4.8815F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -57.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gLeftbackflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightbackflipper", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 57.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.55F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -42.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -42.5F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("gRightbackflipper", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.55F, KeyframeAnimations.posVec(-0.9223F, -4.9142F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))

            .build();
}
