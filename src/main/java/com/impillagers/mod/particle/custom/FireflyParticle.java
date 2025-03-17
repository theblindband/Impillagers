package com.impillagers.mod.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;

@Environment(EnvType.CLIENT)
public class FireflyParticle extends SpriteBillboardParticle {

    public FireflyParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
		super(world, x, y, z);
		this.setSprite(spriteProvider.getSprite(this.random.nextInt(12), 12));
        float field_43369 = (float) Math.toRadians(this.random.nextBoolean() ? -30.0D : 30.0D);
        float field_43370 = this.random.nextFloat();
        float field_43371 = (float) Math.toRadians(this.random.nextBoolean() ? -5.0D : 5.0D);
		this.maxAge = 300;
		this.gravityStrength = 0F;
		//float f = this.random.nextBoolean() ? 0.05F : 0.075F;
		this.scale = 0.25f;
		this.setBoundingBoxSpacing(0.25f, 0.25f);
		this.velocityMultiplier = 1.0F;
		this.collidesWithWorld = true;
	}

	@Override
	public int getBrightness(float tint) {
		int i = super.getBrightness(tint);
		int j = 240;
		int k = i >> 16 & 0xFF;
		return 240 | k << 16;
	}

	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
	}

	public void tick() {
		// Update previous position to current position
		this.prevPosX = this.x;
		this.prevPosY = this.y;
		this.prevPosZ = this.z;

		// Decrease maxAge and check if the particle should be marked as dead
		if (this.maxAge-- <= 0) {
			this.markDead();
		}

		// If the particle is not dead, update its movement and other properties
		if (!this.dead) {
			// Apply random changes to velocity to simulate bug-like movement
			this.velocityX += (this.random.nextDouble() - 0.5) * 0.1;
			this.velocityY += (this.random.nextDouble() - 0.5) * 0.1;
			this.velocityZ += (this.random.nextDouble() - 0.5) * 0.1;

			// Apply gravity to velocityY
			this.velocityY -= this.gravityStrength;

			// Move the particle based on its velocity
			this.move(this.velocityX, this.velocityY, this.velocityZ);

			// Check if the particle is on the ground or has stopped moving horizontally
			if (this.onGround || this.maxAge < 299 && (this.velocityX == 0.0D || this.velocityZ == 0.0D)) {
				this.markDead();
			}

			// Apply velocity damping if the particle is not dead
			if (!this.dead) {
				this.velocityX *= this.velocityMultiplier;
				this.velocityY *= this.velocityMultiplier;
				this.velocityZ *= this.velocityMultiplier;
			}
		}
	}
}