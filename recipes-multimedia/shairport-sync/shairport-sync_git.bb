SUMMARY = "Shairport Sync"
DESCRIPTION = "Shairport Sync is an AirPlay audio player"
LICENSE = "MIT"

DEPENDS += " \
    popt \
    libconfig \
    avahi \
    alsa-lib \
    openssl \
    libpipewire \
    libpulse \
"
RDEPENDS:${PN} += " \
    pipewire \
    avahi \
"

PV = "4.3.4"
S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = " \
    git://github.com/mikebrady/shairport-sync.git;protocol=https;branch=master;tag=${PV} \
    file://shairport-sync.service \
"
LIC_FILES_CHKSUM = "file://../git/LICENSES;md5=9f329b7b34fcd334fb1f8e2eb03d33ff"

inherit autotools systemd pkgconfig

PACKAGECONFIG ??= "stdout"
PACKAGECONFIG[systemd] = ""
PACKAGECONFIG[stdout] = "--with-pipe --with-stdout,"

EXTRA_OECONF="--with-alsa --with-avahi --with-ssl=openssl --with-metadata --with-pa --with-pw"

SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('PACKAGECONFIG', 'systemd', 'shairport-sync.service', '', d)}"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:prepend() {
    cp ${S}/scripts/${PN}.conf ${WORKDIR}/build/scripts/

    if ${@bb.utils.contains('PACKAGECONFIG', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${S}/../shairport-sync.service ${D}${systemd_system_unitdir}
    fi
}

