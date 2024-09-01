SUMMARY = "Snpcast"
DESCRIPTION = "Snapcast is a multiroom client-server audio player"
LICENSE = "GPL-3.0-only"

DEPENDS += " \
    boost \
    alsa-utils \
    avahi \
    alsa-lib \
    libvorbis \
    flac \
    libopus \
    expat \
    soxr \
    libpulse \
    libpipewire \
"
RDEPENDS:${PN} += " \
    alsa-utils \
    avahi \
    alsa-lib \
    libvorbis \
    flac \
    libopus \
    expat \
    soxr \
    soxr-dev \
    libpulse \
    libpipewire \
    pipewire-jack \
"
RDEPENDS:${PN}-server += " \
    pipewire-jack \
"

PV = "0.29.0"
S = "${WORKDIR}/git"

SRC_URI = " \
    git://github.com/badaix/snapcast.git;protocol=https;branch=develop;tag=v${PV} \
    file://snapclient.service \
    file://snapserver.service \
    file://snapserver.conf \
"
LIC_FILES_CHKSUM = "file://../git/LICENSE;md5=7702f203b58979ebbc31bfaeb44f219c"

inherit cmake systemd pkgconfig

EXTRA_OECMAKE = "-DBUILD_TESTS=OFF -DBUILD_STATIC_LIBS=OFF -DBUILD_WITH_PULSE=ON"

PACKAGES = " \
    ${PN}-client \
    ${PN}-server \
    ${PN}-dbg \
    ${PN}-client-doc \
    ${PN}-server-doc \
"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/snapclient.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/snapserver.service ${D}${systemd_system_unitdir}

    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/snapserver.conf ${D}${sysconfdir}/

    # Remove unneeded icons
    rm -rf ${D}${datadir}/pixmaps
}

SYSTEMD_PACKAGES = "${PN}-client ${PN}-server"
SYSTEMD_SERVICE:${PN}-client = "snapclient.service"
SYSTEMD_SERVICE:${PN}-server = "snapserver.service"

FILES:${PN}-client = " \
    ${bindir}/snapclient \
    ${systemd_system_unitdir}/snapclient.service \
"

FILES:${PN}-client-doc = "${mandir}/man1/snapclient*"

FILES:${PN}-server = " \
    ${bindir}/snapserver \
    ${sysconfdir}/snapserver* \
    ${datadir}/snapserver/* \
    ${systemd_system_unitdir}/snapserver.service \
"

FILES:${PN}-server-doc = "${mandir}/man1/snapserver*"
