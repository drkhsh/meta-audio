LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/waveshare/WM8960-Audio-HAT;protocol=https;branch=master"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://kernel_path.patch "

PV = "0.1"
SRCREV = "259d282dc4856318c6961e7d6a94b761308653d3"

S = "${WORKDIR}/git"

inherit module

RPROVIDES:${PN} += "kernel-module-wm8960-soundcard"

do_install:append(){
	install -d ${D}${sysconfdir}/wm8960-soundcard

	install -m 0755 ${S}/*.state ${D}${sysconfdir}/wm8960-soundcard
	install -m 0755 ${S}/*.conf ${D}${sysconfdir}/wm8960-soundcard
}

FILES:${PN} += " /etc/wm8960-soundcard/*"
