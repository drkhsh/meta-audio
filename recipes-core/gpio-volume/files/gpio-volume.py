#!/usr/bin/env python3

import RPi.GPIO as GPIO
import time
import alsaaudio
import os

mixer = os.getenv('MIXER')
vol_up_pin = int(os.getenv('GPIO_VOL_UP'))
vol_dn_pin = int(os.getenv('GPIO_VOL_DOWN'))

m = alsaaudio.Mixer(mixer)
vol = m.getvolume()[0]
is_mute = m.getmute()[0]

GPIO.setmode(GPIO.BCM)

def toggle_mute():
    global is_mute
    m.setmute(1 - is_mute)
    is_mute = m.getmute()[0]
    if is_mute:
        print('Muted')
    else:
        print('Un-muted')

GPIO.setup(vol_up_pin, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(vol_dn_pin, GPIO.IN, pull_up_down=GPIO.PUD_UP)

while True:
    vu = not GPIO.input(vol_up_pin)
    vd = not GPIO.input(vol_dn_pin)
    if vu and not vd:
        if is_mute:
            toggle_mute()
        if vol < 100:
            vol += 5
            m.setvolume(vol)
            vol = m.getvolume()[0]
        print('Volume up Pressed', vol)
    elif vd and not vu:
        if is_mute:
            toggle_mute()
        if vol > 0:
            vol -= 5
            m.setvolume(vol)
            vol = m.getvolume()[0]
        print('Volume down Pressed', vol)
    elif vd and vu:
        toggle_mute()
        time.sleep(0.5)

    time.sleep(0.2)

