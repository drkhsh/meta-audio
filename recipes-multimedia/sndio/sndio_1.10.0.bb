SUMMARY = "A small audio and MIDI framework"
DESCRIPTION = "Sndio is a small audio and MIDI framework part of the OpenBSD project and ported to FreeBSD, Linux and NetBSD."
LICENSE = "ISC"

DEPENDS += " \
    alsa-lib \
    libbsd \
"
RDEPENDS:${PN} += " \
    alsa-lib \
    libbsd \
"

PV = "1.10.0"
S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = "git://sndio.org/git/sndio;protocol=https;branch=master;tag=v${PV}"
LIC_FILES_CHKSUM = "file://../git/LICENSE;md5=2ab025415f530e070f222ce6fae0a031"

inherit autotools systemd pkgconfig

SYSTEMD_SERVICE:${PN} = "sndiod.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

EXTRA_OECONF = "--prefix=/usr --enable-alsa --with-libbsd"

do_configure () {
    cd ${S} && ./configure ${EXTRA_OECONF}
}

do_compile:prepend () {
    cd ${S}
}

do_install:prepend() {
    cd ${S}
}
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${S}/contrib/sndiod.service ${D}/${systemd_unitdir}/system
}

FILES:${PN} += " ${systemd_unitdir}/system/sndiod.service"

