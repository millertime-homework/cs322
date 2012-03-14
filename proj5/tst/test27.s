	.global main
	.align 4
main:
!locals=2, max_args=0
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 2) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t2 assigned to reg %l2
	mov %o0,%l2
! [MOVE (MEM (TEMP 2)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l2]
! [MOVE (VAR 2) (TEMP 2)]
	mov %l2,%l0
	st %l0,[%fp-8]
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  3
!Total insts: 25
