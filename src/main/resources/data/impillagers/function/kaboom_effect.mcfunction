tag @s add victimHolder
execute as @e[type=minecraft:arrow,limit=1,sort=nearest] run impillagers trigger_event_listener kaboom_enchantment @e[tag=victimHolder,limit=1]
execute as @e[tag=victimHolder] run tag @s remove victimHolder
