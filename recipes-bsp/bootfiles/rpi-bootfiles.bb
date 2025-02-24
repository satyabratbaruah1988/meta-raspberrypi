DESCRIPTION = "Closed source binary files to help boot all raspberry pi devices."
LICENSE = "Broadcom-RPi"

LIC_FILES_CHKSUM = "file://LICENCE.broadcom;md5=c403841ff2837657b2ed8e5bb474ac8d"

inherit deploy nopackages

include recipes-bsp/common/raspberrypi-firmware.inc

INHIBIT_DEFAULT_DEPS = "1"

DEPENDS = "rpi-config rpi-cmdline"

COMPATIBLE_MACHINE = "^rpi$"

S = "${RPIFW_S}/boot"

PR = "r3"

do_deploy() {
    install -d ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${S}/bootcode.bin ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${S}/start.elf ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${S}/start_cd.elf ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${S}/start_db.elf ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${S}/start_x.elf ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}

    cp ${S}/fixup.dat ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${S}/fixup_cd.dat ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${S}/fixup_db.dat ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${S}/fixup_x.dat ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}

    touch ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/${PN}-${PV}.stamp
#    for i in ${S}/*.elf ; do
#        cp $i ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
#    done
#    for i in ${S}/*.dat ; do
#        cp $i ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
#    done
#    for i in ${S}/*.bin ; do
#        cp $i ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
#    done
#
#    # Add stamp in deploy directory
#    touch ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/${PN}-${PV}.stamp
}

do_deploy[depends] += "rpi-config:do_deploy rpi-cmdline:do_deploy"

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

