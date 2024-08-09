SUMMARY = "Using a Raspberry Pi as a Bluetooth® speaker with PipeWire"
HOMEPAGE = "https://github.com/fdanis-oss/pw_wp_bluetooth_rpi_speaker"
LICENSE = "MIT"

SRCREV = "57569e46b506782e503129f791809b2aae2b0ea6"

SRC_URI = " \
    git://github.com/fdanis-oss/pw_wp_bluetooth_rpi_speaker.git;protocol=https;branch=master"
SRC_URI[sha256sum] = "11dad404a3a658d5741169194e438177061ee6e4fcb9b0ed2c9c248caacdadd5"

LIC_FILES_CHKSUM = "file://README.md;md5=77ef16d0fe681bc157b0bb00be90d9e4"

RDEPENDS:${PN} += " \
    python3-core \
    python3-dbus \
"

inherit systemd

SYSTEMD_SERVICE:${PN} = "speaker-agent.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

S = "${WORKDIR}/git"

do_install () {
    install -d -m 0755 ${D}${bindir}
    install -m 0755 ${S}/speaker-agent.py ${D}${bindir}

    install -d -m 0755 ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/.config/systemd/user/speaker-agent.service ${D}${systemd_system_unitdir}
}

FILES:${PN} = " \
    ${bindir}/speaker-agent.py \
    ${systemd_system_unitdir}/speaker-agent.service \
    "

